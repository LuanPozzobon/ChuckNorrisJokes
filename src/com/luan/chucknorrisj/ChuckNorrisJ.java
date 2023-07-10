package com.luan.chucknorrisj;

import com.luan.chucknorrisj.utils.InputReader;
import java.util.List;

/**
 *
 * @author luanp
 */
public class ChuckNorrisJ {

    public static void main(String[] args) {
                
        InputReader sc = new InputReader();
        ClientHttp client = new ClientHttp();
        
        String joke;
        List<String> categories;
        while(true){
            System.out.println("01 - Random Joke");
            System.out.println("02 - List Categories");
            System.out.println("03 - Exit");

            switch(sc.getNextInt()){
                case 1:
                    joke = client.getJoke("https://api.chucknorris.io/jokes/random");

                    System.out.println(joke);
                    break;
                case 2:
                    categories = client.getCategories("https://api.chucknorris.io/jokes/categories");

                    for(String category : categories){
                        System.out.println(category);
                    }

                    System.out.print("Which category do you want to see: ");
                    String category = sc.getNextLine();

                    joke = client.getJoke("https://api.chucknorris.io/jokes/random?category=" + category);
                    
                    System.out.println(joke);
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}
