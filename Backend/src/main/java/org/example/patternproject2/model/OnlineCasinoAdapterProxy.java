package org.example.patternproject2.model;


import lombok.AllArgsConstructor;

@AllArgsConstructor

public class OnlineCasinoAdapterProxy implements OnlineCasino{
    private final Avtomat avtomat;

    @Override
    public String play(Long id, int coin, double lacky) {
        if(avtomat.getAge(id)<18){
            return "you cannot play this game";
        }
        lacky+=avtomat.getLucky(id);
        if (coin <= 0) {
            throw new IllegalArgumentException("Coin amount must be positive");
        }
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if(lacky>3){
            avtomat.play(id,coin);

            upLucky(id);
            return "win";
        }else if(lacky<3){
            avtomat.play(id,-coin);
            return "lose";
        }else{
            return "win +0 coin";
        }
    }

    @Override
    public void upLucky(Long id) {
        avtomat.upLucky(id);
    }




}
