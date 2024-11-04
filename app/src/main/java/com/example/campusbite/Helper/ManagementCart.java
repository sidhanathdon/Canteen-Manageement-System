package com.example.campusbite.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.campusbite.Interface.ChangeNumberItemsListner;
import com.example.campusbite.Domain.foodDomain;

import java.util.ArrayList;

public class ManagementCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(foodDomain item){
        ArrayList<foodDomain> listFood = getListCart() ;
        boolean existAlready = false;
        int n =0;
        for (int i = 0;i< listFood.size();i++)
        {
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }

        tinyDB.putListObject("CardList",listFood);
        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<foodDomain> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    public void minusNumberFood(ArrayList<foodDomain> listfood, int position, ChangeNumberItemsListner changeNumberItemsListner) {
        if(listfood.get(position).getNumberInCart() == 1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListner.changed();
    }

    public void plusNumberFoood(ArrayList<foodDomain> listfood, int position, ChangeNumberItemsListner changeNumberItemsListner){
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList",listfood);
        changeNumberItemsListner.changed();
    }

    public Double getTotalFee(){
        ArrayList<foodDomain> listfood = getListCart();
        double fee = 0;
        for (int i=0; i<listfood.size();i++){
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberInCart());
        }
        return fee;

    }

    public int getToatalnoItems()
    {
        ArrayList<foodDomain> listfood = getListCart();
        int items = 0;
        for (int i=0; i<listfood.size();i++){
            int a  = listfood.get(i).getNumberInCart();
            items = items + a;
        }
        return items;
    }
}


