/*
Edgar Martinez Martinez
EXM180001
This project is meant to organize the away team and the home team of a baseball game
and the data is retrieved play by play. After each team information is displayed, the 
leaders of each category is displayed.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String inFile = scan.next();
        
        Hashtable<String, String> hS = new Hashtable<>();
        hS.put("1-3","Out");
        hS.put("2-3", "Out");
        hS.put("3u", "Out");
        hS.put("3-1", "Out");
        hS.put("3-4", "Out");
        hS.put("4-3", "Out");
        hS.put("5-3", "Out");
        hS.put("6-3", "Out");
        hS.put("7-3", "Out");
        hS.put("8-3", "Out");
        hS.put("P1", "Out");
        hS.put("P2", "Out");
        hS.put("P3", "Out");
        hS.put("P4", "Out");
        hS.put("P5", "Out");
        hS.put("P6", "Out");
        hS.put("F7", "Out");
        hS.put("F8", "Out");
        hS.put("F9", "Out");
        hS.put("DP", "Out");
        hS.put("FC", "Out");
        hS.put("K", "Strikeout");
        hS.put("1B", "Hits");
        hS.put("2B", "Hits");
        hS.put("3B", "Hits");
        hS.put("HR", "Hits");
        hS.put("BB", "Walks");
        hS.put("S1", "Sacrifices");
        hS.put("S2", "Sacrifices");
        hS.put("S3", "Sacrifices");
        hS.put("S5", "Sacrifices");
        hS.put("SF7", "Sacrifices");
        hS.put("SF8", "Sacrifices");
        hS.put("SF9", "Sacrifices");
        hS.put("HBP", "Hit By Pitch");
        hS.put("E1", "Errors");
        hS.put("E2", "Errors");
        hS.put("E3", "Errors");
        hS.put("E4", "Errors");
        hS.put("E5", "Errors");
        hS.put("E6", "Errors");
        hS.put("E7", "Errors");
        hS.put("E8", "Errors");
        hS.put("E9", "Errors");
        Hashtable <String, Player> players = new Hashtable<>();
        File in = new File(inFile);
        while(!(in.exists()))
        {
            System.out.println("The file name was not found please enter a new file name");
            inFile = scan.next();
            in = new File(inFile);
            
        }
        String name = "", play = "";
        Scanner inputFile = new Scanner(in);
        String current;
        char HomeOrA;
        while(inputFile.hasNext())
        {
                current = inputFile.next(); 
                if(current.charAt(0) == 'A')
                    HomeOrA = 'A';
                else
                    HomeOrA = 'H';
                name = inputFile.next();
                play = inputFile.next();
                Player p = null;
                if(!(players.containsKey(name)))
                    p = new Player(name);
                else
                    p = players.get(name);
                if(hS.get(play) == "Out")
                {
                    p.setOuts(p.getOuts() + 1);
                    p.setAtBats(p.getAtBats() + 1);
                }
                else if(hS.get(play) == "Strikeout")
                {
                    p.setAtBats(p.getAtBats() + 1);
                    p.setStkOuts(p.getStkOuts() + 1);
                }
                else if(hS.get(play) == "Hits")
                {
                    p.setAtBats(p.getAtBats() + 1);
                    p.setHits(p.getHits() + 1);
                }
                else if(hS.get(play) == "Walks")
                    p.setWalks(p.getWalks() + 1);
                else if (hS.get(play) == "Sacrifices")
                    p.setSacrifice(p.getSacrifice() + 1);
                else if(hS.get(play) == "Hit By Pitch")
                    p.setHtByPtch(p.getHtByPtch() + 1);
                else if(hS.get(play) == "Errors")
                    p.setErrors(p.getErrors() + 1);
                p.setHomeOrAway(HomeOrA);
                HomeOrA = 'C';
                players.put(name, p);
                
        }
        PrintWriter out = new PrintWriter("leaders.txt");        
        out.println("AWAY");
        HomeOrAway(players, 'A', out);
        out.println();
        out.println("HOME");
        HomeOrAway(players, 'H', out);
        out.println();
        out.println("LEAGUE LEADERS");
        printLeaders(players, out);
        out.close();
    }
    
    /*
    This function takes in a hashTable of all players, the current team it is looking at and then
    displays each characters stat in alphabetic order.
    */
    public static void HomeOrAway(Hashtable p, char hA, PrintWriter out)
    {
        LinkedList LL = new LinkedList();
        LL = sortedLinkedList(p, hA);
        LL.forEach((k) -> {
        out.printf("%s\t%d" + "\t" +
        ((Player)k).getHits() + "\t" + ((Player)k).getWalks() + "\t" +
        ((Player)k).getStkOuts()+ "\t" + ((Player)k).getHtByPtch() + "\t" +
        ((Player)k).getSacrifice() + "\t%.3f\t%.3f" +
        "\n",((Player)k).getName(), ((Player)k).getAtBats(), ((Player)k).calcBatAvg(), ((Player)k).calcOnBsePrcnt());
        });
    }
    
    /*
    This function uses the hashTable that is passed in and displays each stat
    and then the characters that have corresponding values to that for stats.
    The function displays the leaders for 6 different categories.
    */
    
    public static void printLeaders(Hashtable p, PrintWriter out)
    {
        double highNum = 214748600;
        int counter = 0;
        int counter2 = 0;
        int highScore = 0, highScore2 = 0, highScore3 = 0;
        double highThird;
        double highSecond = 0;
        LinkedList home = sortedLinkedList(p, 'H');
        LinkedList away = sortedLinkedList(p, 'A');
        
        double highS = findHighest(p, "BA", highNum, highNum);
        out.println("BATTING AVERAGE");
        out.printf("%.3f\t", highS);
        counter = 0;
        counter2 = 0;
        for(int i = 0; i < away.size(); i++)
        {
            if(((Player)away.get(i)).calcBatAvg() == highS)
            {
                if(counter == 0)
                    out.print(((Player)away.get(i)).getName());
                else
                    out.print(", " + ((Player)away.get(i)).getName());
                counter++;
            }
        }
        for(int i = 0; i < home.size(); i++)
        {
            if(((Player)home.get(i)).calcBatAvg() == highS)
            {
                if(counter < 1)
                    out.print(((Player)home.get(i)).getName());
                else
                    out.print(", " + ((Player)home.get(i)).getName());
                counter++;
            }
        }
        out.println();
        if(counter < 3)
        {
            highSecond = findHighest(p, "BA", highS, highNum);
            out.printf("%.3f\t", highSecond);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).calcBatAvg() == highSecond)
                {
                    if(counter2 == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter2++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).calcBatAvg() == highSecond)
                {
                    if(counter2 == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter2++;
                }
            }
            out.println();
        }
        if(counter + counter2 < 3)
        {
            counter = 0;
            highThird = findHighest(p, "BA", highS, highSecond);
            out.printf("%.3f\t", highThird);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).calcBatAvg() == highThird)
                {
                    if(counter == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                       out.print(", " + ((Player)away.get(i)).getName());
                    counter++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).calcBatAvg() == highThird)
                {
                    if(counter == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter++;
                }
            }
            out.println();
        }
        out.println();
        highS = findHighest(p, "OB", highNum, highNum);
        out.println("ON-BASE PERCENTAGE");
        out.printf("%.3f\t", highS);
        counter = 0;
        counter2 = 0;
        for(int i = 0; i < away.size(); i++)
        {
            if(((Player)away.get(i)).calcOnBsePrcnt()== highS)
            {
                if(counter == 0)
                    out.print(((Player)away.get(i)).getName());
                else
                    out.print(", " + ((Player)away.get(i)).getName());
                counter++;
            }
        }
        for(int i = 0; i < home.size(); i++)
        {
            if(((Player)home.get(i)).calcOnBsePrcnt()== highS)
            {
                if(counter == 0)
                    out.print(((Player)home.get(i)).getName());
                else
                    out.print(", " + ((Player)home.get(i)).getName());
                counter++;
            }
        }
        out.println();
        if(counter < 3)
        {
            highSecond = findHighest(p, "OB", highS, highNum);
            out.printf("%.3f\t", highSecond);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).calcOnBsePrcnt()== highSecond)
                {
                    if(counter2 == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter2++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).calcOnBsePrcnt()== highSecond)
                {
                    if(counter2 == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter2++;
                }
            }
            out.println();
        }
        if(counter + counter2 < 3)
        {
            counter = 0;
            highThird = findHighest(p, "OB", highS, highSecond);
            out.printf("%.3f\t", highThird);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).calcOnBsePrcnt()== highThird)
                {
                    if(counter == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).calcOnBsePrcnt()== highThird)
                {
                    if(counter == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter++;
                }
            }
            out.println();
        }
        out.println();
        highScore = (int)findHighest(p, "H", highNum, highNum);
        out.println("HITS");
        out.printf("%s\t", highScore);
        counter = 0;
        counter2 = 0;
        for(int i = 0; i < away.size(); i++)
        {
            if(((Player)away.get(i)).getHits() == highScore)
            {
                if(counter == 0)
                    out.print(((Player)away.get(i)).getName());
                else
                    out.print(", " + ((Player)away.get(i)).getName());
                counter++;
            }
        }
        for(int i = 0; i < home.size(); i++)
        {
            if(((Player)home.get(i)).getHits() == highScore)
            {
                if(counter == 0)
                    out.print(((Player)home.get(i)).getName());
                else
                    out.print(", " + ((Player)home.get(i)).getName());
                counter++;
            }
        }
        out.println();
        if(counter < 3)
        {
            highScore2 = (int)findHighest(p, "H", highScore, highNum);
            out.printf("%s\t", highScore2);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getHits() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter2++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getHits() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter2++;
                }
            }
            out.println();
        }
        if(counter + counter2< 3)
        {
            counter = 0;
            highScore3 = (int)findHighest(p, "H", highScore, highScore2);
            out.printf("%s\t", highScore3);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getHits() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getHits() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter++;
                }
            }
            out.println();
        }
        out.println();
        highScore = (int)findHighest(p, "W", highNum, highNum);
        out.println("WALKS");
        out.printf("%s\t", highScore);
        counter = 0;
        counter2 = 0;
        for(int i = 0; i < away.size(); i++)
        {
            if(((Player)away.get(i)).getWalks() == highScore)
            {
                if(counter == 0)
                    out.print(((Player)away.get(i)).getName());
                else
                    out.print(", " + ((Player)away.get(i)).getName());
                counter++;
            }
        }
        for(int i = 0; i < home.size(); i++)
        {
            if(((Player)home.get(i)).getWalks() == highScore)
            {
                if(counter == 0)
                    out.print(((Player)home.get(i)).getName());
                else
                    out.print(", " + ((Player)home.get(i)).getName());
                counter++;
            }
        }
        out.println();
        if(counter < 3)
        {
            highScore2 = (int)findHighest(p, "W", highScore, highNum);
            out.printf("%s\t", highScore2);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getWalks() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter2++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getWalks() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter++;
                }
            }
            out.println();
        }
        if(counter + counter2 < 3)
        {
            counter = 0;
            highScore3 = (int)findHighest(p, "W", highScore, highScore2);
            out.printf("%s\t", highScore3);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getWalks() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getWalks() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter++;
                }
            }
            out.println();
        }
        out.println();
        highScore = findLowest(p, -1, -1);
        out.println("STRIKEOUTS");
        out.printf("%s\t", highScore);
        counter = 0;
        counter2 = 0;
        for(int i = 0; i < away.size(); i++)
        {
            if(((Player)away.get(i)).getStkOuts()== highScore)
            {
                if(counter == 0)
                    out.print(((Player)away.get(i)).getName());
                else
                    out.print(", " + ((Player)away.get(i)).getName());
                counter++;
            }
        }
        for(int i = 0; i < home.size(); i++)
        {
            if(((Player)home.get(i)).getStkOuts() == highScore)
            {
                if(counter == 0)
                    out.print(((Player)home.get(i)).getName());
                else
                    out.print(", " + ((Player)home.get(i)).getName());
                counter++;
            }
        }
        out.println();
        if(counter < 3)
        {
            highScore2 = findLowest(p, highScore, -1);
            out.printf("%s\t", highScore2);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getStkOuts() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter2++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getStkOuts() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter2++;
                }
            }
            out.println();
        }
        if(counter + counter2 < 3)
        {
            counter = 0;
            highScore3 = findLowest(p, highScore, highScore2);
            out.printf("%s\t", highScore3);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getStkOuts() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getStkOuts() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter++;
                }
            }
            out.println();
        }
        out.println();
        highScore = (int)findHighest(p, "HBP", highNum, highNum);
        out.println("HIT BY PITCH");
        out.printf("%s\t", highScore);
        counter = 0;
        counter2 = 0;
        for(int i = 0; i < away.size(); i++)
        {
            if(((Player)away.get(i)).getHtByPtch()== highScore)
            {
                if(counter == 0)
                    out.print(((Player)away.get(i)).getName());
                else
                    out.print(", " + ((Player)away.get(i)).getName());
                counter++;
            }
        }
        for(int i = 0; i < home.size(); i++)
        {
            if(((Player)home.get(i)).getHtByPtch() == highScore)
            {
                if(counter == 0)
                    out.print(((Player)home.get(i)).getName());
                else
                    out.print(", " + ((Player)home.get(i)).getName());
                counter++;
            }
        }
        out.println();
        if(counter < 3)
        {
            highScore2 = (int)findHighest(p, "HBP", highScore, highNum);
            out.printf("%s\t", highScore2);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getHtByPtch() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter2++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getHtByPtch() == highScore2)
                {
                    if(counter2 == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter2++;
                }
            }
            out.println();
        }
        if(counter + counter2 < 3)
        {
            counter = 0;
            highScore3 = (int)findHighest(p, "HBP", highScore, highScore2);
            out.printf("%s\t", highScore3);
            for(int i = 0; i < away.size(); i++)
            {
                if(((Player)away.get(i)).getHtByPtch() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)away.get(i)).getName());
                    else
                        out.print(", " + ((Player)away.get(i)).getName());
                    counter++;
                }
            }
            for(int i = 0; i < home.size(); i++)
            {
                if(((Player)home.get(i)).getHtByPtch() == highScore3)
                {
                    if(counter == 0)
                        out.print(((Player)home.get(i)).getName());
                    else
                        out.print(", " + ((Player)home.get(i)).getName());
                    counter++;
                }
            }
            out.println();
        }
        out.println();
    }
    
    /*
    This function is used to find the numbers that will be used for the league leaders
    on each stat except for the strikeouts
    The function differentiates between each stat so it can find it in the hashtable
    then it returns the highest number, or if specified, it returns either the second highest/lowest number
    or the third highest/lowest number.
    */
    
    public static double findHighest(Hashtable p, String stat, double s, double t)
    {
        double highest = 0.0;
        Set<String> keySet = p.keySet();
        Iterator<String> keyIt = keySet.iterator();
        if(stat == "K")
        {
            highest = findLowest(p, (int)s, (int)t);
        }
        else
        {
            while(keyIt.hasNext()) {
                String key = keyIt.next();
                if(stat == "BA")
                {
                    if(((Player)p.get(key)).calcBatAvg() > highest && ((Player)p.get(key)).calcBatAvg() != s)
                    {
                        if(((Player)p.get(key)).calcBatAvg() != t)
                        highest = ((Player)p.get(key)).calcBatAvg();
                    }
                }
                else if(stat == "OB")
                {
                    if(((Player)p.get(key)).calcOnBsePrcnt() > highest && ((Player)p.get(key)).calcOnBsePrcnt() != s)
                    {
                        if(((Player)p.get(key)).calcOnBsePrcnt() != t)
                        highest = ((Player)p.get(key)).calcOnBsePrcnt();
                    }
                }
                else if(stat == "H")
                {
                    if(((Player)p.get(key)).getHits() > highest && ((Player)p.get(key)).getHits() != s)
                    {
                        if(((Player)p.get(key)).getHits() != t)
                            highest = ((Player)p.get(key)).getHits();
                    }
                }
                else if(stat == "W")
                {
                    if(((Player)p.get(key)).getWalks() > highest && ((Player)p.get(key)).getWalks() != s)
                    {
                        if(((Player)p.get(key)).getWalks() != t)
                            highest = ((Player)p.get(key)).getWalks();}
                }
                else if(stat == "HBP")
                {
                    if(((Player)p.get(key)).getHtByPtch() > highest && ((Player)p.get(key)).getHtByPtch() != s)
                    {
                        if(((Player)p.get(key)).getHtByPtch() != t)
                            highest = ((Player)p.get(key)).getHtByPtch();
                    }
                }
            }
        }
        return highest;
    }
    
    
    /*
    This function takes in a hashtable and returns a linkedlist based on whether the character hA is
    Home or Away, the function first adds the player data from the hashtable into the linked list then
    it alphabetizes the linked list and lastly it returns the linked list.
    */
    public static LinkedList sortedLinkedList(Hashtable p, char hA)
    {
        LinkedList LL = new LinkedList();
        p.forEach((k, v) -> {
            if((((Player)p.get(k)).getHomeOrAway()) == hA)
                LL.add(v);
            });
        boolean swapped = true;
        Player temp1 = null;
        int i = 1;
        do
        {
            swapped = false;
            for(int j = 0; j < LL.size() - 1; j++)
            {
                if(((Player)LL.get(j)).getName().compareToIgnoreCase(((Player)LL.get(j + 1)).getName()) > 0)
                {
                    temp1 = (Player)LL.get(j);
                    LL.set(j, LL.get(j + 1));
                    LL.set(j+1, temp1);
                    swapped = true;
                }
            }
        }while(swapped);
        return LL;
    }
    
    /*
    This function is used to find the lowest int value and is used solely to find the number 
    for strikeouts when displaying it as league leaders
    */
    
    public static int findLowest(Hashtable p, int s, int t)
    {
        int lowest = 214748600;
        Set<String> keySet = p.keySet();
        Iterator<String> keyIt = keySet.iterator();
        while(keyIt.hasNext())
        {
            String key = keyIt.next();
            if(((Player)p.get(key)).getStkOuts() < lowest && ((Player)p.get(key)).getStkOuts()!= s) 
            { 
                if(((Player)p.get(key)).getStkOuts()!= t)
                {
                    lowest = ((Player)p.get(key)).getStkOuts();
                }
            }
        }
        return lowest;
    }
}
