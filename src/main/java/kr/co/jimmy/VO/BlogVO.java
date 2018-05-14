package kr.co.jimmy.VO;

public class BlogVO {

	private String id;
	private String blogTitle;
	private String logoFile;
	private String cateName;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	@Override
	public String toString() {
		return "BlogVO [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + ", cateName=" + cateName
				+ "]";
	}

}
