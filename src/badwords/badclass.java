/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badwords;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author mahjoub
 */
public class badclass {
    private static badclass badwords;
     JSONObject jsonObject;
     private badclass() {
        JSONParser parser = new JSONParser();
        
        
        try {
            
            
            Object obj = parser.parse(new FileReader("src/badwords/words.json"));
            jsonObject = (JSONObject) obj;
            
            
        } catch (IOException ex) {
            Logger.getLogger(badclass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(badclass.class.getName()).log(Level.SEVERE, null, ex);
        }

          
    }
    
    
    
    public static badclass getInstance()
    {
        if(badwords ==null)
        {
            badwords=new badclass();
        }
        return badwords;
    }
    
    
    public void  ShowBadWords() {
        
        JSONArray msg = (JSONArray) jsonObject.get("bad");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
    }
    
    public void edit(String ss)
    {
        String [] s=ss.trim().split(" ");
        JSONArray list = (JSONArray) jsonObject.get("bad");
        for(int i=0; i< s.length; i++)
         {
            list.add(s[i]);
          }
       
        

        jsonObject.put("bad", list);

        try (FileWriter file = new FileWriter("src/badwords/words.json")) {

            file.write(jsonObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

   
    }
    
    public  boolean existe(String s)
    {
        JSONArray msg = (JSONArray) jsonObject.get("bad");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                if(s.contains(iterator.next()))
                return true;
            }
       
        return false;
    }
    public  int nb(String s)
    {
        
        int j=0;
        
        
        JSONArray msg = (JSONArray) jsonObject.get("bad");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                if(s.indexOf(iterator.next())>=0)
                j++;
            }
       
        
        return j;
    }
}
