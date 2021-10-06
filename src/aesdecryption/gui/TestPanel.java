package aesdecryption.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import aesdecryption.SecuredGCMUsage;

public class TestPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtEncryptedPayload;
	private JTextField txtAESKey;
	private JTextField txtAESIV;
	private JTextArea txtDecryptedPayload;
	private JTextField txtAAD;
	private JCheckBox cbxURLDecode;

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{821, 0};
		gridBagLayout.rowHeights = new int[]{14, 14, 150, 14, 20, 14, 20, 14, 20, 23, 14, 250, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		cbxURLDecode = new JCheckBox("URL Decode AES Parameters");
		cbxURLDecode.setSelected(true);
		GridBagConstraints gbc_cbxURLDecode = new GridBagConstraints();
		gbc_cbxURLDecode.anchor = GridBagConstraints.WEST;
		gbc_cbxURLDecode.insets = new Insets(0, 0, 5, 0);
		gbc_cbxURLDecode.gridx = 0;
		gbc_cbxURLDecode.gridy = 0;
		add(cbxURLDecode, gbc_cbxURLDecode);
		
		JLabel lblEncryptedPayload = new JLabel("Encrypted Payload (base64)");
		GridBagConstraints gbc_lblEncryptedPayload = new GridBagConstraints();
		gbc_lblEncryptedPayload.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEncryptedPayload.anchor = GridBagConstraints.NORTH;
		gbc_lblEncryptedPayload.insets = new Insets(0, 0, 5, 0);
		gbc_lblEncryptedPayload.gridx = 0;
		gbc_lblEncryptedPayload.gridy = 1;
		add(lblEncryptedPayload, gbc_lblEncryptedPayload);
		
		txtEncryptedPayload = new JTextArea();
		txtEncryptedPayload.setLineWrap(true);
		GridBagConstraints gbc_txtEncryptedPayload = new GridBagConstraints();
		gbc_txtEncryptedPayload.fill = GridBagConstraints.BOTH;
		gbc_txtEncryptedPayload.insets = new Insets(0, 0, 5, 0);
		gbc_txtEncryptedPayload.gridx = 0;
		gbc_txtEncryptedPayload.gridy = 2;
		add(txtEncryptedPayload, gbc_txtEncryptedPayload);
		txtEncryptedPayload.setColumns(10);
		
		JLabel lblAESKey = new JLabel("AES Key (base64)");
		GridBagConstraints gbc_lblAESKey = new GridBagConstraints();
		gbc_lblAESKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAESKey.anchor = GridBagConstraints.NORTH;
		gbc_lblAESKey.insets = new Insets(0, 0, 5, 0);
		gbc_lblAESKey.gridx = 0;
		gbc_lblAESKey.gridy = 3;
		add(lblAESKey, gbc_lblAESKey);
		
		txtAESKey = new JTextField();
		GridBagConstraints gbc_txtAESKey = new GridBagConstraints();
		gbc_txtAESKey.anchor = GridBagConstraints.NORTH;
		gbc_txtAESKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAESKey.insets = new Insets(0, 0, 5, 0);
		gbc_txtAESKey.gridx = 0;
		gbc_txtAESKey.gridy = 4;
		add(txtAESKey, gbc_txtAESKey);
		txtAESKey.setColumns(10);
		
		JLabel lblAESIV = new JLabel("AES IV (base64)");
		GridBagConstraints gbc_lblAESIV = new GridBagConstraints();
		gbc_lblAESIV.anchor = GridBagConstraints.NORTH;
		gbc_lblAESIV.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAESIV.insets = new Insets(0, 0, 5, 0);
		gbc_lblAESIV.gridx = 0;
		gbc_lblAESIV.gridy = 5;
		add(lblAESIV, gbc_lblAESIV);
		
		txtAESIV = new JTextField();
		GridBagConstraints gbc_txtAESIV = new GridBagConstraints();
		gbc_txtAESIV.anchor = GridBagConstraints.NORTH;
		gbc_txtAESIV.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAESIV.insets = new Insets(0, 0, 5, 0);
		gbc_txtAESIV.gridx = 0;
		gbc_txtAESIV.gridy = 6;
		add(txtAESIV, gbc_txtAESIV);

		txtAESIV.setColumns(10);
		
		JButton btnDecryptPayload = new JButton("Decrypt Payload");
		btnDecryptPayload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					generatePayload();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}) ;
		
		JLabel lblAAD = new JLabel("AAD");
		GridBagConstraints gbc_lblAAD = new GridBagConstraints();
		gbc_lblAAD.anchor = GridBagConstraints.WEST;
		gbc_lblAAD.insets = new Insets(0, 0, 5, 0);
		gbc_lblAAD.gridx = 0;
		gbc_lblAAD.gridy = 7;
		add(lblAAD, gbc_lblAAD);
		
		txtAAD = new JTextField();
		GridBagConstraints gbc_txtAAD = new GridBagConstraints();
		gbc_txtAAD.insets = new Insets(0, 0, 5, 0);
		gbc_txtAAD.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAAD.gridx = 0;
		gbc_txtAAD.gridy = 8;
		add(txtAAD, gbc_txtAAD);
		txtAAD.setColumns(10);
		GridBagConstraints gbc_btnDecryptPayload = new GridBagConstraints();
		gbc_btnDecryptPayload.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDecryptPayload.insets = new Insets(0, 0, 5, 0);
		gbc_btnDecryptPayload.gridx = 0;
		gbc_btnDecryptPayload.gridy = 9;
		add(btnDecryptPayload, gbc_btnDecryptPayload);
		
		JLabel lblDecryptedPayload = new JLabel("Decrypted Payload");
		GridBagConstraints gbc_lblDecryptedPayload = new GridBagConstraints();
		gbc_lblDecryptedPayload.anchor = GridBagConstraints.NORTH;
		gbc_lblDecryptedPayload.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDecryptedPayload.insets = new Insets(0, 0, 5, 0);
		gbc_lblDecryptedPayload.gridx = 0;
		gbc_lblDecryptedPayload.gridy = 10;
		add(lblDecryptedPayload, gbc_lblDecryptedPayload);
		
		txtDecryptedPayload = new JTextArea();
		txtDecryptedPayload.setEditable(false);
		GridBagConstraints gbc_txtDecryptedPayload = new GridBagConstraints();
		gbc_txtDecryptedPayload.fill = GridBagConstraints.BOTH;
		gbc_txtDecryptedPayload.gridx = 0;
		gbc_txtDecryptedPayload.gridy = 11;
		add(txtDecryptedPayload, gbc_txtDecryptedPayload);

	}
	
	private void generatePayload() throws Exception {
		String sEncryptedPayload = txtEncryptedPayload.getText();
		String sKey = txtAESKey.getText();
		String sIV = txtAESIV.getText();
		String sAAD = txtAAD.getText();
		String sDecryptedPayload = "";
		String sError = "";
		boolean bURLDecode = false;
		
		bURLDecode = cbxURLDecode.isSelected();

		if (bURLDecode) {
			sEncryptedPayload = URLDecoder.decode(sEncryptedPayload, StandardCharsets.UTF_8);
			sKey = URLDecoder.decode(sKey, StandardCharsets.UTF_8);
			sIV = URLDecoder.decode(sIV, StandardCharsets.UTF_8);
		}
		
//		sEncodedPayload = SecuredGCMUsage.encryptData(sPayload);
//		sIV = SecuredGCMUsage.sIV;
//		aKey = SecuredGCMUsage.aKey;
//		
//		System.out.println(Base64.getEncoder().encodeToString(aKey));
//		
//		sEncryptedKey = SecuredRSAUsage.encryptPayload(aKey, sPublicKeyPEM);
//		
//		sSignature = SecuredRSAUsage.signSHA256RSA(sPayload, sPrivateKeyPEM);
//		
//		sNotificationPayload = ("encryptedPayload=" + URLEncoder.encode(sEncodedPayload, StandardCharsets.UTF_8) + "&encryptedSessionKey=" + URLEncoder.encode(sEncryptedKey, StandardCharsets.UTF_8) + "&iv=" + URLEncoder.encode(sIV, StandardCharsets.UTF_8) + "&payloadSignature=" + URLEncoder.encode(sSignature, StandardCharsets.UTF_8));
		//sNotificationPayload = ("encryptedPayload=" + sEncodedPayload + "&encryptedSessionKey=" + sEncryptedKey + "&iv=" + sIV + "&payloadSignature=" + sSignature);
		
//		Decrypt part to test if it all works fine
//		sEncryptedKey = SecuredRSAUsage.decryptPayload(sEncryptedKey, sPrivateKeyPEM);
//		aKey1 = Base64.getDecoder().decode(sEncryptedKey);
//		SecuredGCMUsage.aKey = aKey1;
		sDecryptedPayload = SecuredGCMUsage.decryptData(sEncryptedPayload, sAAD, sKey, sIV);

		sError = SecuredGCMUsage.ERROR_MSG;
		if (sError != "") {
			sDecryptedPayload = sError;
		}

		txtDecryptedPayload.setText(sDecryptedPayload);
		txtDecryptedPayload.setLineWrap(true);
		txtDecryptedPayload.setWrapStyleWord(true);
	
		
	}
	
}
