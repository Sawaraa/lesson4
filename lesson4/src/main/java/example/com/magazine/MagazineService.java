package example.com.magazine;

import example.com.user.User;
import example.com.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class MagazineService {

    private List<Magazine> listofMagazine = new ArrayList<>();
    private static MagazineService magazineService;

    MagazineService(){

    }

    public static MagazineService getMagazineService(){
        if(magazineService == null){
            magazineService = new MagazineService();
        }
        return magazineService;
    }

    public List<Magazine> getListOfMagazine(){
        return listofMagazine;
    }

    public void saveMagazine(Magazine magazine){
        listofMagazine.add(magazine);
    }

}
