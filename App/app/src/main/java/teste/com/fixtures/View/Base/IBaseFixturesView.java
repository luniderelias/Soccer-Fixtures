package teste.com.fixtures.View.Base;

public interface IBaseFixturesView {


    void showLoading();
    void configureRecyclerView();
    void setAdapter();
    void getData();
    void showTryAgainSnackbar();
    void setScrollListener();
    void updateList();
    void sortItems();
    void checkConnectivity();
    void setLoadingVisibility(int visibility);
    void onTextChange();
}
