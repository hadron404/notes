/**
 * 租赁，表示某个顾客租了一部影片.
 *
 * @author zhouqiang
 * @date 2022/2/17
 */
class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        this._movie = movie;
        this._daysRented = daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }
}
