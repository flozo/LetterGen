package de.flozo;

import de.flozo.data.*;
import de.flozo.latex.core.*;
import de.flozo.latex.letter.AddressField;
import de.flozo.latex.letter.BackaddressField;
import de.flozo.latex.tikz.*;
import de.flozo.latex.tikz.Rectangle;

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

    public static final String MASTER_CONFIG_FILE_NAME = "master.config";


    public static void main(String[] args) {


        Settings settings = new Settings(MASTER_CONFIG_FILE_NAME);

        PropertyMap letterGeometryMap = new PropertyMap(ConfigGroup.LETTER_GEOMETRY);
        letterGeometryMap.updateDefaults(settings);
        LetterGeometry geometry = new LetterGeometry(letterGeometryMap);

        PropertyMap senderMap = new PropertyMap(ConfigGroup.SENDER_DATA);
        senderMap.updateDefaults(settings);
        Address senderData = new Address(senderMap);

        PropertyMap receiverMap = new PropertyMap(ConfigGroup.RECEIVER_DATA);
        receiverMap.updateDefaults(settings);
        Address receiverData = new Address(receiverMap);

        PropertyMap general = new PropertyMap(ConfigGroup.LETTER_GENERAL);
        general.updateDefaults(settings);
        LetterGeneral letterGeneral = new LetterGeneral(general);


        System.out.println(letterGeneral.getDateFormat());
        System.out.println(letterGeneral.isDraftModeOn());


        Command2 documentclass = Documentclass.createWithOptions(DocumentClassName.STANDALONE, "12pt", "tikz", "multi", "crop");

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

        Command2 usetikzlibrary = new Command2.Command2Builder(CommandName.USETIKZLIBRARY.getString())
                .body(
                        "positioning",
                        "math",
                        "colorbrewer",
                        "backgrounds",
                        "matrix")
                .bodyTerminator(StatementTerminator.COMMA)
                .build();
        Command2 standaloneenv = new Command2.Command2Builder(CommandName.STANDALONEENV.getString())
                .body("tikzpicture")
                .build();

        String pdfauthor = senderData.getFirstName() + " " + senderData.getLastName();
        Command2 hypersetup = new Command2.Command2Builder(CommandName.HYPERSETUP.getString())
                .body(
                        "colorlinks=true",
                        String.format("urlcolor=%s", geometry.getUrlHyperlinkColor()),
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

        Rectangle backgroundRectangle = new Rectangle.RectangleBuilder(0, 0, geometry.getPaperWidth(), geometry.getPaperHeight())
                .fillColor(new Color(StandardColorName.NONE))
                .drawColor(new Color(StandardColorName.NONE))
                .build();

        LayerEnvironment onBackgroundLayer = new LayerEnvironment("background", backgroundRectangle.getInline());


//        PerforationMark perforationMark = new PerforationMark(geometryProperties);

//        LayerEnvironment onForegroundLayer = new LayerEnvironment("foreground", perforationMark.getStatement());
//
        AddressField addressField = new AddressField(geometry, receiverData);
        BackaddressField backaddressField = new BackaddressField(geometry, senderData);

        Node cell11 = new Node.NodeBuilder(4.0, 24, new Command2.Command2Builder(CommandName.FAICON.getString()).body("map-marker-alt").build().getInline()).build();
        Node cell12 = new Node.NodeBuilder(4.0, 24, "My street 25\\12345 City").build();
        MatrixOfNodes matrix = new MatrixOfNodes.MatrixBuilder("contact", 4.0, 24, Anchor.NORTH_WEST)
                .addRow(cell11.getInline(), cell12.getInline(), cell11.getInline())
                .addRow(cell12.getInline(), cell11.getInline(), cell12.getInline())
                .build();

        for (String line : matrix.getBlock()) {
            System.out.println(line);
        }

        FormattedExpressionList tikzpictureBody = new FormattedExpressionList.FormattedExpressionListBuilder()
                .append(onBackgroundLayer.getBlock())
                .append(addressField.getAddressField())
                .append(backaddressField.getBackaddressText())
                .append(backaddressField.getSeparationLine())
//                .append(onForegroundLayer.getBlock())
                .build();

        Environment3 tikzpicture = new Environment3.Environment3Builder(EnvironmentName.TIKZPICTURE)
                .optionalArguments("inner xsep=0pt", "inner ysep=0pt", "trim left=0pt", "trim right=" + geometry.getPaperWidth() + "cm")
                .body(tikzpictureBody.getBlock())
                .build();


        FormattedExpressionList documentBody = new FormattedExpressionList.FormattedExpressionListBuilder()
                .append(pgflayers.getBlock())
                .append(tikzpicture.getBlock())
                .append()
                .build();

        Environment3 document = new Environment3.Environment3Builder(EnvironmentName.DOCUMENT)
                .body(documentBody.getBlock())
                .build();

        Environment2 test = new Environment2.Environment2Builder(EnvironmentName.PGFONLAYER)
                .requiredArguments("layerName")
                .body("test line1", "test line2")
                .build();

        Environment3 test2 = new Environment3.Environment3Builder(EnvironmentName.PGFONLAYER)
                .requiredArguments("layerName")
                .body("test line1", "test line2")
                .build();


        for (String line : test.getBlock()) {
            System.out.println(line);
        }
        for (String line : test2.getBlock()) {
            System.out.println(line);
        }


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
