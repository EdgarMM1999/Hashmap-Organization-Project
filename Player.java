/*
Edgar Martinez Martinez EXM180001
 */
public class Player {
    private String name;
    private int stkOuts = 0;
    private int walks = 0;
    private int htByPtch = 0;
    private int hits = 0;
    private int atBats = 0;
    private int outs = 0;
    private int sacrifice = 0;
    private char homeOrAway;
    private int errors = 0;
    
    Player()
    {}
    Player(String s)
    {
        name = s;
    }
    public int getErrors()
    {return errors;}
    public char getHomeOrAway()
    {return homeOrAway;}
    public String getName()
    {return name;}
    public int getStkOuts()
    {return stkOuts;}
    public int getWalks()
    {return walks;}
    public int getHtByPtch()
    {return htByPtch;}
    public int getHits()
    {return hits;}
    public int getAtBats()
    {return atBats;}
    public int getOuts()
    {return outs;}
    public int getSacrifice()
    {return sacrifice;}
    
    public void setErrors(int e)
    {errors = e;}
    public void setHomeOrAway(char h)
    {homeOrAway = h;}
    public void setName(String s)
    {name = s;}
    public void setStkOuts(int s)
    {stkOuts = s;}
    public void setWalks(int w)
    {walks = w;}
    public void setHtByPtch(int h)
    {htByPtch = h;}
    public void setHits(int h)
    {hits = h;}
    public void setAtBats(int a)
    {atBats = a;}
    public void setOuts(int o)
    {outs = o;}
    public void setSacrifice(int s)
    {sacrifice = s;}
    /*
    The functions below calculate the batters average and the on base percentage by 
    returning the formulas that are used to get the numbers.
    Unless the numerator or denominator are 0 then it returns 0.
    */
    
    public double calcBatAvg()
    {
        if((0.0 + hits + stkOuts + outs) != 0.0 && (double)hits != 0.0)
            return (1.0 * hits) / (0.0 + hits + stkOuts + outs);
        else 
            return 0.0;
    }
    
    public double calcOnBsePrcnt()
    {
        double plateA = (hits + walks + htByPtch + stkOuts + outs + sacrifice + errors);
        if(plateA != 0.0)
            return (1.0 * (hits + walks + htByPtch)) / + plateA;
        else 
            return 0.0;
    }
}
