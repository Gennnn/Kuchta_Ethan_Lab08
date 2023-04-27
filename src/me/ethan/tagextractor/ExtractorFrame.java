package me.ethan.tagextractor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExtractorFrame extends JFrame {
    JPanel mainPnl = new JPanel();
    JPanel sourcePnl = new JPanel();
    JPanel tagPnl = new JPanel();
    JPanel displayPnl = new JPanel();
    JPanel savePnl = new JPanel();

    JFileChooser sourceChooser = new JFileChooser();
    JFileChooser tagChooser = new JFileChooser();

    JButton openSourceButton = new JButton();
    JButton openTagButton = new JButton();
    JButton saveButton = new JButton();

    JTextPane sourceText = new JTextPane();
    JTextPane tagText = new JTextPane();
    public JTextPane displayText = new JTextPane();
    public JScrollPane displayScroll = new JScrollPane();
    public static BufferedReader sourceFile;
    public static BufferedReader tagFile;

    public ExtractorFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        createSourcePnl();
        createTagPnl();
        createDisplayPnl();
        createSavePnl();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPnl.add(sourcePnl, c);
        c.gridx = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        mainPnl.add(tagPnl, c);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = 1;
        mainPnl.add(displayPnl, c);
        c.fill = GridBagConstraints.REMAINDER;
        c.gridwidth = 1;

        c.gridx = 3;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        mainPnl.add(savePnl, c);
        add(mainPnl);
        setSize(800,600);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    public void createSourcePnl() {
        sourcePnl = new JPanel();
        sourcePnl.setLayout(new GridBagLayout());
        JPanel sourcePnlInternal = new JPanel();
        sourcePnlInternal.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        sourceChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory().getPath());
        sourceChooser.setFileFilter(filter);
        openSourceButton = new JButton("Select File");
        sourceText.setText("Selected File: None");
        sourceText.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));
        openSourceButton.addActionListener(
                (ActionEvent ae) ->
                {
                    int r = sourceChooser.showOpenDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        String name = sourceChooser.getSelectedFile().getName();
                        try {
                            sourceFile = new BufferedReader(new FileReader(sourceChooser.getSelectedFile()));
                        } catch (FileNotFoundException e) {
                            name = "Invalid file!";
                            e.printStackTrace();
                        }
                        sourceText.setText("Selected File: " + name);
                    }
                }
        );
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.ipadx = 5;
        c.ipady = 5;
        c.insets = new Insets(5,5,5,5);
        sourceText.setEditable(false);
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        sourceText.getStyledDocument().setParagraphAttributes(0, sourceText.getStyledDocument().getLength(), center, false);
        //tagText.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        c.anchor = GridBagConstraints.LINE_START;
        sourcePnlInternal.setBorder(new BevelBorder(BevelBorder.LOWERED));
        sourcePnlInternal.add(openSourceButton, BorderLayout.WEST);
        c.gridx = 1;
        c.gridwidth = 1;
        sourceText.setBackground(UIManager.getColor ( "Panel.background" ));
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.REMAINDER;
        openSourceButton.setBackground(Color.RED);
        sourcePnlInternal.add(sourceText, BorderLayout.CENTER);
        sourcePnlInternal.setPreferredSize(new Dimension(300, 50));
        sourcePnl.setBorder(new TitledBorder("Source File"));

        c.fill = GridBagConstraints.HORIZONTAL;
        sourcePnl.add(sourcePnlInternal, c);
    }
    public void createTagPnl() {
        tagPnl = new JPanel();
        tagPnl.setLayout(new GridBagLayout());
        JPanel tagPnlInternal = new JPanel();
        tagPnlInternal.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        tagChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory().getPath());
        tagChooser.setFileFilter(filter);
        openTagButton = new JButton("Select File");
        tagText.setText("Selected File: None");
        tagText.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));
        openTagButton.addActionListener(
                (ActionEvent ae) ->
                {
                    int r = tagChooser.showOpenDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        String name = tagChooser.getSelectedFile().getName();
                        try {
                            tagFile = new BufferedReader(new FileReader(tagChooser.getSelectedFile()));
                        } catch (FileNotFoundException e) {
                            name = "Invalid file!";
                            e.printStackTrace();
                        }
                        tagText.setText("Selected File: " + name);
                    }
                }
        );
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.ipadx = 5;
        c.ipady = 5;
        c.insets = new Insets(5,5,5,5);
        tagText.setEditable(false);
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        tagText.getStyledDocument().setParagraphAttributes(0, tagText.getStyledDocument().getLength(), center, false);
        //tagText.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        c.anchor = GridBagConstraints.LINE_START;
        tagPnlInternal.setBorder(new BevelBorder(BevelBorder.LOWERED));
        tagPnlInternal.add(openTagButton, BorderLayout.WEST);
        c.gridx = 1;
        c.gridwidth = 1;
        tagText.setBackground(UIManager.getColor ( "Panel.background" ));
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.REMAINDER;
        openTagButton.setBackground(Color.RED);
        tagPnlInternal.add(tagText, BorderLayout.CENTER);
        tagPnlInternal.setPreferredSize(new Dimension(300, 50));

        c.fill = GridBagConstraints.HORIZONTAL;
        tagPnl.setBorder(new TitledBorder("Tag File"));
        tagPnl.add(tagPnlInternal, c);
    }
    public void createDisplayPnl() {
        displayPnl = new JPanel();
        displayPnl.setLayout(new GridBagLayout());
        JPanel displayPnlInternal = new JPanel();
        displayPnlInternal.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.ipadx = 5;
        c.ipady = 5;
        c.insets = new Insets(5,5,5,5);
        displayText.setEditable(false);
        displayScroll = new JScrollPane(displayText);
        //tagText.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        displayText.setBackground(UIManager.getColor ( "Panel.background" ));
        displayPnlInternal.add(displayScroll);
        displayScroll.setPreferredSize(new Dimension(450, 350));
        displayPnl.add(displayPnlInternal, c);
    }
    public void createSavePnl() {
        savePnl = new JPanel();
        JPanel savePnlInternal = new JPanel();
        savePnlInternal.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton saveBtn = new JButton("Save");
        JButton runBtn = new JButton("Run");
        JButton quitBtn = new JButton("Quit");
        runBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    try {
                        ExtractorEvent.extract(sourceFile, tagFile);
                        displayText.setText(ExtractorEvent.returnWords());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        savePnlInternal.add(saveBtn, c);
        saveBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    File writeFile = new File(System.getProperty("user.home") + File.separator + "Desktop");
                    Path fileName = Path.of(System.getProperty("user.home") + File.separator + "Desktop/extractor_out.txt");
                    try {
                        Files.writeString(fileName, displayText.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        );
        saveBtn.setPreferredSize(new Dimension(75,75));
        c.gridy = 1;
        savePnlInternal.add(runBtn, c);
        runBtn.setPreferredSize(new Dimension(75,75));

        c.gridy = 2;
        savePnlInternal.add(quitBtn, c);
        quitBtn.setPreferredSize(new Dimension(75,75));
        quitBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    System.exit(0);

                }
        );

        savePnlInternal.setPreferredSize(new Dimension(150, 350));

        savePnl.add(savePnlInternal);

    }
}
