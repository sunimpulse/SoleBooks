package com.blanke.solebook.core.column.persenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.blanke.solebook.bean.BookColumn;
import com.blanke.solebook.constants.Constants;
import com.blanke.solebook.core.column.view.ColumnView;
import com.blanke.solebook.utils.AvosCacheUtils;
import com.socks.library.KLog;

import java.util.List;

/**
 * Created by Blanke on 16-2-22.
 */
public class ColumnPersenterImpl extends ColumnPersenter {
    @Override
    public void getColumnData(BookColumn parentBookColumn, boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        AvosCacheUtils.CacheELseNetwork(parentBookColumn.getSubs().getQuery(BookColumn.class))
                .orderByAscending("order")
                .findInBackground(new FindCallback<BookColumn>() {
                    @Override
                    public void done(List<BookColumn> list, AVException e) {
//                        KLog.json(list.toString());
                        if (isViewAttached()) {
                            ColumnView view = getView();
                            view.setData(list);
                            if (e == null) {
                                view.showContent();
                            } else {
                                view.showError(e, pullToRefresh);
                            }
                        }
                    }
                });
    }
}
