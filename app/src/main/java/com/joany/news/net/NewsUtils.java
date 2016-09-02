package com.joany.news.net;

import com.joany.news.base.BaseApplication;
import com.joany.news.utils.Utils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsUtils {
    private static RxNewsService rxNewsService;
    private static NewsUtils instance;

    private NewsUtils(){
    }

    public static NewsUtils getInstance(){
        if(instance == null) {
            synchronized (NewsUtils.class) {
                if(instance == null) {
                    instance = new NewsUtils();
                }
            }
        }
        return instance;
    }

    public RxNewsService getRxNewsService(){
        if(rxNewsService == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //Starting with OkHttp v3 OkHttpClient is immutable and so are the interceptors.
            // To add the logging interceptor (or any other for that mater), use OkHttpClient.Builder.
//            OkHttpClient okHttpClient = new OkHttpClient();
//            okHttpClient.interceptors().add(httpLoggingInterceptor);
//            okHttpClient.interceptors().add(interceptor);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor).addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASEURL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            rxNewsService = retrofit.create(RxNewsService.class);
        }
        return  rxNewsService;
    }

    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            boolean isNetworkReachable = Utils.isNetworkReachable(BaseApplication.getContext());
            Request request = chain.request();
            if(!isNetworkReachable){
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response response = chain.proceed(request);
            if(isNetworkReachable) {
                int maxAge = 60 * 60;
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, max-age"+maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, only-if-cached,max-stale="+maxStale)
                        .build();
            }
            return response;
        }
    };
}
