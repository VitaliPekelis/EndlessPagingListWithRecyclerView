package com.vit.pek;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;

import java.util.List;

class MyDataSourceFactory extends DataSource.Factory<Integer, DataItem> {

    private DataRepository repository;

    public MyDataSourceFactory(DataRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public DataSource<Integer, DataItem> create() {
        return new MyDataSource(repository);
    }

    /**
    * class DataSource
    * */
    static class MyDataSource extends PositionalDataSource<DataItem> {

        private DataRepository repository;

        public MyDataSource(DataRepository repository) {
            this.repository = repository;
        }

        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<DataItem> callback) {
            List<DataItem> data = repository.getData(params.requestedLoadSize);
            callback.onResult(data, params.requestedStartPosition);
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<DataItem> callback) {
            List<DataItem> data = repository.getData(params.loadSize);
            callback.onResult(data);
        }
    }


}
