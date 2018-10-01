//package com.franctan.mypassport.ui.main.listprofilesfragment;
//
//import com.franctan.firebaserepo.daos.ProfilesListDao;
//import com.franctan.models.Profile;
//import com.franctan.mypassport.preferences.RxPreferences;
//
//import java.util.List;
//
//import io.reactivex.Observable;
//import io.reactivex.functions.Function3;
//
//public class JavaScratch {
//
//    public void test() {
//
//        RxPreferences rxPreferences;
//        ProfilesListDao profilesListDao;
//
//        Observable<List<Profile>> profilesDaoRx = profilesListDao.getProfileListObserver();
//        Observable<String> filterRx = rxPreferences.getFilterAsObservable();
//        Observable<String> sortRx = rxPreferences.getSortAsObservable();
//
//        Observable<List<Profile>> listObservable = Observable.combineLatest(profilesDaoRx, filterRx, sortRx, new Function3<List<Profile>, String, String, List<Profile>>() {
//            @Override
//            public List<Profile> apply(List<Profile> profiles, String s, String s2) throws Exception {
//                return profiles;
//            }
//        });
//
//
//    }
//}
