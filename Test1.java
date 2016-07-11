/**
 * Created by Thibaud on 11/07/2016.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.ConnectException;
import java.io.IOException;


public class Test1 {

    public URL a_Url = new URL("https","datainfogreffe.fr","/api/records/1.0/search/?dataset=entreprises-immatriculees-en-2016&sort=date_d_immatriculation&facet=libelle&facet=code_postal&facet=ville&facet=region&facet=greffe&facet=date_d_immatriculation&refine.code_postal=75008&refine.date_d_immatriculation=2016%2F02%2F15");

    public String postURL(URL a_Url, String a_sParamsToPost) {
        StringBuilder o_oSb = new StringBuilder();

        //recup du saut de ligne
        String o_sLineSep = null;
        o_sLineSep = System.getProperty("line.separator");


        HttpURLConnection o_oUrlConn = (HttpURLConnection) a_Url.openConnection();
        o_oUrlConn.setRequestMethod("POST");
        o_oUrlConn.setAllowUserInteraction(false);
        //envoyer des params
        o_oUrlConn.setDoOutput(true);

        //poster les params
        PrintWriter o_oParamWriter = new PrintWriter(o_oUrlConn.getOutputStream());

        o_oParamWriter.print(a_sParamsToPost);
        //fermer le post avant de lire le resultat ... logique
        o_oParamWriter.flush();
        o_oParamWriter.close();

        //Lire la reponse
        InputStream o_oResponse = o_oUrlConn.getInputStream();
        BufferedReader o_oBufReader = new BufferedReader(new InputStreamReader(o_oResponse));
        String sLine;

        while ((sLine = o_oBufReader.readLine()) != null)
        {
            o_oSb.append(sLine);
            o_oSb.append(o_sLineSep);
        }
        //deconnection
        o_oUrlConn.disconnect();
    }
}
