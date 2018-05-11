package kr.co.jimmy.VO;

public class BlogAdminVO {

	private int cateNo;
	private String id;
	private String cateName;
	private String description;
	private String regDate;
	private int postCount;
	
	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BlogAdminVO [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", description="
				+ description + ", regDate=" + regDate + ", postCount=" + postCount + "]";
	}

}
