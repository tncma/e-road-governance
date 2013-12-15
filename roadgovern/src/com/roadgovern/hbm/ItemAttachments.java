package com.roadgovern.hbm;

// Generated Dec 15, 2013 12:26:56 AM by Hibernate Tools 3.4.0.CR1

/**
 * ItemAttachments generated by hbm2java
 */
public class ItemAttachments implements java.io.Serializable {

	private Integer attachmentId;
	private ItemLog itemLog;
	private byte[] attachment;
	private String attachmentDesc;
	private String attachmentName;

	public ItemAttachments() {
	}

	public ItemAttachments(ItemLog itemLog) {
		this.itemLog = itemLog;
	}

	public ItemAttachments(ItemLog itemLog, byte[] attachment,
			String attachmentDesc, String attachmentName) {
		this.itemLog = itemLog;
		this.attachment = attachment;
		this.attachmentDesc = attachmentDesc;
		this.attachmentName = attachmentName;
	}

	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public ItemLog getItemLog() {
		return this.itemLog;
	}

	public void setItemLog(ItemLog itemLog) {
		this.itemLog = itemLog;
	}

	public byte[] getAttachment() {
		return this.attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentDesc() {
		return this.attachmentDesc;
	}

	public void setAttachmentDesc(String attachmentDesc) {
		this.attachmentDesc = attachmentDesc;
	}

	public String getAttachmentName() {
		return this.attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

}