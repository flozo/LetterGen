package de.flozo;

import de.flozo.data.*;
import de.flozo.io.ConfigDirectory;
import de.flozo.io.File;
import de.flozo.io.MasterConfigFile;
import de.flozo.io.OutputFile;
import de.flozo.latex.core.*;
import de.flozo.latex.letter.*;
import de.flozo.latex.tikz.Layer;
import de.flozo.latex.tikz.LayerEnvironment;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // constants
    public static final String VERSION_NUMBER = "0.1";
    public static final String VERSION_DATE = "2022-05-17";
    public static final String REPO_URL = "https://github.com/flozo/LetterGen";
    public static final String VERSION_INFO_LATEX_HEADER = String.format("%% =====  LaTeX code generated by LetterGen v%1$s (%2$s)\n%% =====  LetterGen by flozo (%3$s)",
            VERSION_NUMBER, VERSION_DATE, REPO_URL);
    public static final String VERSION_INFO_PDF_META_DATA = String.format("LetterGen v%1$s (%2$s); visit %3$s",
            VERSION_NUMBER, VERSION_DATE, REPO_URL);

    //    public static final String MASTER_CONFIG_FILE_NAME = ConfigGroup.MASTER.getPropertyKey();
    public static final String MASTER_CONFIG_FILE_NAME = "master.config";


    public static void main(String[] args) {

        ConfigDirectory configDirectory = ConfigDirectory.fromDefaultDirectory();
        MasterConfigFile masterConfigFile = MasterConfigFile.withDefaultFileName(configDirectory);
        masterConfigFile.readProperties();

        PropertyMap letterGeometry = PropertyMap.createWithDefaults(ConfigGroup.LETTER_GEOMETRY);
        PropertyMap letterGeneral = PropertyMap.createWithDefaults(ConfigGroup.LETTER_GENERAL);
        PropertyMap letterColors = PropertyMap.createWithDefaults(ConfigGroup.LETTER_COLORS);
        PropertyMap senderMap = PropertyMap.createWithDefaults(ConfigGroup.SENDER_DATA);
        PropertyMap receiverMap = PropertyMap.createWithDefaults(ConfigGroup.RECEIVER_DATA);
        PropertyMap enclosures = PropertyMap.createFromFile(ConfigGroup.LETTER_ENCLOSURES, masterConfigFile);

        letterGeometry.updateWithConfigFileSettings(masterConfigFile);
        letterGeneral.updateWithConfigFileSettings(masterConfigFile);
        letterColors.updateWithConfigFileSettings(masterConfigFile);
        senderMap.updateWithConfigFileSettings(masterConfigFile);
        receiverMap.updateWithConfigFileSettings(masterConfigFile);

        LetterGeometry geometry = new LetterGeometry(letterGeometry);
        LetterColor color = new LetterColor(letterColors);
        Address senderData = new Address(senderMap);
        Address receiverData = new Address(receiverMap);
        LetterGeneral general = new LetterGeneral(letterGeneral);
        File letterBodyTextFile = File.fromPath(Paths.get(masterConfigFile.getParentDirectory().toString(), general.getBodyTextFile()));

        Signature signature = new Signature(general, geometry, color, senderData);


        Command documentclass = Documentclass.createWithOptions(DocumentClassName.STANDALONE, "12pt", "tikz", "multi", "crop");

        PackageList packageList = new PackageList(documentclass);
        packageList.add(PackageName.INPUTENC, "utf8")
                .add(PackageName.FONTENC, "T1")
                .add(PackageName.BABEL, "german")
                .add(PackageName.HYPERXMP)
                .add(PackageName.FIRASANS, "sfdefault", "scaled=1.0098")
                .add(PackageName.NEWTXSF)
                .add(PackageName.FONTAWESOME5)
                .add(PackageName.CSQUOTES, "autostyle=true")
                .add(PackageName.ENUMITEM)
                .add(PackageName.MICROTYPE, "activate={true, nocompatibility}", "final", "tracking=true", "kerning=true", "spacing=true", "factor=1100", "stretch=8", "shrink=8")
                .add(PackageName.TIKZ)
                .add(PackageName.HYPERREF, "unicode");


        Command usetikzlibrary = new GenericCommand.Builder(CommandName.USETIKZLIBRARY.getString())
                .body(
                        "positioning",
                        "math",
                        "colorbrewer",
                        "backgrounds",
                        "matrix")
                .bodyTerminator(StatementTerminator.COMMA)
                .build();
        Command standaloneenv = new GenericCommand.Builder(CommandName.STANDALONEENV.getString())
                .body("tikzpicture")
                .build();

        String pdfauthor = senderData.getFirstName() + " " + senderData.getLastName();
        Command hypersetup = new GenericCommand.Builder(CommandName.HYPERSETUP.getString())
                .body(
                        "colorlinks=true",
                        String.format("urlcolor=%s", color.getUrlHyperlinkColor().getString()),
                        "pdftitle={Letter}",
                        String.format("pdfauthor={%s}", pdfauthor),
                        String.format("pdfdate={%s}", LocalDate.now()),
                        String.format("pdfproducer={%s}", VERSION_INFO_PDF_META_DATA),
                        String.format("pdfcontactemail={%s}", senderData.getEmailAddress())
                )
                .bodyTerminator(StatementTerminator.COMMA)
                .build();

        List<String> packageSettings = new ArrayList<>();
        packageSettings.add(usetikzlibrary.getInline());
        packageSettings.add(standaloneenv.getInline());
        packageSettings.addAll(hypersetup.getBlock());
        List<String> preamble = new ArrayList<>(packageList.getBlock());
        preamble.addAll(packageSettings);

        Layer pgflayers = new Layer.LayerBuilder("background", "forebackground", "main", "foreground")
                .build();

        Page pageOne = new Page(geometry, color);


        SenderField senderField = new SenderField(geometry, color, senderData);

        LayerEnvironment onForeBackgroundLayer = new LayerEnvironment("forebackground", senderField.getMatrix().getBlock());

        AddressField addressField = new AddressField(geometry, color, receiverData);
        BackaddressField backaddressField = new BackaddressField(geometry, color, senderData);
        Headline headline = new Headline(geometry, color, senderData);
        SubjectField subjectField = new SubjectField(geometry, color, "My subject");
        DateField dateField = new DateField(geometry, color, "City", "2022-06-22");
        Enclosure enclosureField = new Enclosure(general, geometry, color, enclosures.keyValueMap());

        Body letterBody = new Body(geometry, color, letterBodyTextFile.getLines());

//        Body letterBody = new Body(geometry, color, "this is a text this is a text this is a text " +
//                "this is a text this is a text this is a text this is a text this is a text " +
//                "this is a text this is a text this is a text this is a text this is a text " +
//                "this is a text this is a text this is a text this is a text\\\\this is a text this is a text " +
//                "this is a text this is a text this is a text this is a text");

        PerforationMark perforationMark = new PerforationMark(geometry, color);
        FoldingMark1 foldingMark1 = new FoldingMark1(geometry, color);
        FoldingMark2 foldingMark2 = new FoldingMark2(geometry, color);


        ExpressionList tikzpictureBody = new FormattedExpressionList.Builder()
                .append(pageOne.getPage())
                .append(onForeBackgroundLayer.getBlock())
                .append(addressField.getAddressField())
                .append(backaddressField.getBackaddressText())
                .append(backaddressField.getSeparationLine())
                .append(headline.getBlock())
                .append(subjectField.generate())
                .append(dateField.generate())
                .append(letterBody.getBlock())
                .append(perforationMark.getLine())
                .append(foldingMark1.getLine())
                .append(foldingMark2.getLine())
                .append(enclosureField.generate())
                .append(signature.generate())
                .build();

        Environment tikzpicture = new Environment.Builder(EnvironmentName.TIKZPICTURE)
                .optionalArguments("inner xsep=0pt", "inner ysep=0pt", "trim left=0pt", "trim right=" + Length.inCentimeter(geometry.getPaperWidth()).getFormatted())
                .body(tikzpictureBody.getBlock())
                .build();


        ExpressionList documentBody = new FormattedExpressionList.Builder()
                .append(pgflayers.getBlock())
                .append(tikzpicture.getBlock())
                .append()
                .build();

        Environment document = new Environment.Builder(EnvironmentName.DOCUMENT)
                .body(documentBody.getBlock())
                .build();


        // Assemble final code
        List<String> finalCode = new ArrayList<>(preamble);
        finalCode.addAll(document.getBlock());

        String fileName = "test_output.tex";
        String directory = "/tmp";

        OutputFile outputFile = new OutputFile(directory, fileName, finalCode);
        if (outputFile.create(true, true)) {
            System.out.println("[output] Done!");
        } else {
            System.out.println("[output] Something went wrong!");
        }
    }
}
