package org.example.patternproject2.services;

import lombok.AllArgsConstructor;
import org.example.patternproject2.model.Bucmaker;
import org.example.patternproject2.model.OnlineCasino;


@AllArgsConstructor
public class CasinoFacade {
    Bucmaker offlineCasino;
    OnlineCasino onlineCasino;
    public String playAllVariants(Long id, int coin, double lacky){
        String a = "";
        a+="Ofline casino : "+ offlineCasino.play(id,coin,lacky) +"\n";

       a+="OnlineCasino : "+ onlineCasino.play(id,coin,lacky);

       return a;

    }
}
