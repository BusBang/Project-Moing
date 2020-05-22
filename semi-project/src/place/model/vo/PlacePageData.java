package place.model.vo;

import java.util.ArrayList;

public class PlacePageData {
		private ArrayList<Place> pList;
		private String pageNavi;
		
		
		public PlacePageData() {
			super();
			// TODO Auto-generated constructor stub
		}
		public PlacePageData(ArrayList<Place> pList, String pageNavi) {
			super();
			this.pList = pList;
			this.pageNavi = pageNavi;
		}
		public ArrayList<Place> getpList() {
			return pList;
		}
		public void setpList(ArrayList<Place> pList) {
			this.pList = pList;
		}
		public String getPageNavi() {
			return pageNavi;
		}
		public void setPageNavi(String pageNavi) {
			this.pageNavi = pageNavi;
		}

		
}
