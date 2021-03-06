package com.example.desk.api;



import com.example.desk.entity.CommentBean;
import com.example.desk.entity.Desk;
import com.example.desk.entity.MyState;
import com.example.desk.entity.Seat;
import com.example.desk.entity.ShuoShuo;
import com.example.desk.entity.Status;
import com.example.desk.entity.T1;
import com.example.desk.entity.T2;
import com.example.desk.entity.T3;
import com.example.desk.entity.T4;
import com.example.desk.entity.T5;
import com.example.desk.entity.U2;
import com.example.desk.entity.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface APIService {

    //请求所有的说说
    @POST("talk/request")
    Observable<ArrayList<ShuoShuo>> RequestData();

    //请求某条说说下具体的评论和回复
    @POST("talk/request2")
    Observable<CommentBean> RequestData2(@Query("iduser_content") String iduser_content);

    //提交评论
    @POST("comreply/comment")
    Observable<T1> AddComment(@Query("userid") String userid, @Query("iduser_content") String iduser_content, @Query("content") String content);

    //提交回复
    @POST("comreply/reply")
    Observable<T2> AddReply(@Query("userid") String userid, @Query("replyContent") String replyContent, @Query("replyComId") String replyComId);
    //发表说说
    @Multipart
    @POST("upload")
    Observable<Status> uploadImgs(@Part List<MultipartBody.Part> file, @Query("data2") String data, @Query("userid") String userid);

    @POST("doRegister")
    Observable<T3> registeruser(@Query("userid") String userid,@Query("password") String password,@Query("college") String college,@Query("classs") String classs,@Query("birthday") String birthday,@Query("email") String email,@Query("gender") String gender);

    @POST("login")
    Observable<User> loginuser(@Query("userid") String userid,@Query("password") String  password);

    @POST("seat/queryseatinfo")
    Observable<List<Seat>> QuerySeatInfo();

    @POST("seat/queryempteyseat")
    Observable<List<Desk>> QueryEmptySeat(@Query("location") String location,@Query("classroom") String classroom);

    @POST("seat/chooseseat")
    Observable<T4> ChooseSeat(@Query("location") String location, @Query("classroom") String classroom, @Query("seatnumber") String seatnumber, @Query("userid") String userid,@Query("rqcord") String rqcord);

    @POST("seat/enduse")
    Observable<T5> EndUse(@Query("userid") String userid);
    @POST("seat/changestatus")
    Observable<T5> ChangeStatus(@Query("userid") String userid);

    @POST("seat/jieshuzanli")
    Observable<T5> JieshuZanli(@Query("userid") String userid);

    @POST("seat/seemystate")
    Observable<MyState> SeeMystate(@Query("userid") String userid);

    @POST("meinfo")
    Observable<U2> SeeMe(@Query("userid") String userid);
    //上传头像
    @Multipart
    @POST("uploadtouxiang")
    Observable<T3> uploadTouxiangImgs(@Part MultipartBody.Part file, @Query("userid") String userid);
}
