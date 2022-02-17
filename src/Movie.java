/**
 * 影片，简单的纯数据类.
 *
 * @author zhouqiang
 * @date 2022/2/17
 */
 class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    private int _priceCode;


    public Movie(String title, int priceCode) {
        this._title = title;
        this._priceCode = priceCode;
    }

    public String getTitle() {
        return _title;
    }


    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int priceCode) {
        this._priceCode = priceCode;
    }
}
