/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Homework3;

import org.checkerframework.checker.units.qual.A;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    static int blackFlag=1;
    static int whiteFlag=1;
    static int blackGrade=1,whiteGrade=1; //标定在某种规则的手牌下，黑白双方需要拿来比较的牌的下标
    static int whiteDecor[] = new int[7],whiteNum[] = new int[7];
    static int blackDecor[] = new int[7],blackNum[] = new int[7];

    public int turnDecorToNum(char ch){
        int ans=0;
        switch (ch){
            case 'D':ans=1;break; //方片
            case 'S':ans=2;break; //黑桃
            case 'H':ans=3;break; //红桃
            case 'C':ans=4;break; //梅花
        }
        return ans;
    }

    public int turnNumberToNum(char ch){
        int ans=0;
        switch (ch){
            case '2':ans=1;break;
            case '3':ans=2;break;
            case '4':ans=3;break;
            case '5':ans=4;break;
            case '6':ans=5;break;
            case '7':ans=6;break;
            case '8':ans=7;break;
            case '9':ans=8;break;
            case 'T':ans=9;break;
            case 'J':ans=10;break;
            case 'Q':ans=11;break;
            case 'K':ans=12;break;
            case 'A':ans=13;break;
        }
        return ans;
    }

    public int whichCardIsThis(int num[],int decor[]){
        int grade=1,flag=1;

        //判断对子
        for (int i=4;i>0;i--){
            if (num[i]==num[i+1]) {
                grade=2;
                flag=i;
                break;
            }
        }

        //判断两对
        int duiziNum=0;
        for (int i=1;i<5;i++){
            if (num[i]==num[i+1]) {
                duiziNum++;
                i++;
                flag=i;
            }
        }
        if (duiziNum==2) grade=3;

        //判断三条
        for (int i=3;i>0;i--){
            if (num[i]==num[i+1]&&num[i+1]==num[i+2]){
                flag=i;
                grade=4;
                break;
            }
        }

        //判断顺子或同花或同花顺
        boolean boo=false;
        if (num[1]==num[2]&&num[2]==num[3]&&num[3]==num[4]&&num[4]==num[5]) {grade=5; boo=true;}
        if (decor[1]==decor[2]&&decor[2]==decor[3]&&decor[3]==decor[4]&&decor[4]==decor[5]) grade=6;
        if (grade==6&&boo) grade=7;
        flag=5;

        if (num[0]==1) whiteFlag=flag; else blackFlag=flag;
        return grade;
    }

    public String whoWins(String[] black,String[] white){

        //处理Black数据
        for (int i=1;i<=5;i++) {
            blackDecor[i] = new App().turnDecorToNum(black[i].charAt(1));
            blackNum[i] = new App().turnNumberToNum(black[i].charAt(0));
        }
        int t;
        for (int i=1;i<5;i++)
            for (int j=i+1;j<=5;j++)
                if (blackNum[i]>blackNum[j]){
                    t=blackNum[i]; blackNum[i]=blackNum[j]; blackNum[j]=t;
                    t=blackDecor[i]; blackDecor[i]=blackDecor[j]; blackDecor[j]=t;
                }

        //处理White数据
        for (int i=1;i<=5;i++) {
            whiteDecor[i] = new App().turnDecorToNum(white[i].charAt(1));
            whiteNum[i] = new App().turnNumberToNum(white[i].charAt(0));
        }
        for (int i=1;i<5;i++)
            for (int j=i+1;j<=5;j++)
                if (whiteNum[i]>whiteNum[j]){
                    t=whiteNum[i]; whiteNum[i]=whiteNum[j]; whiteNum[j]=t;
                    t=whiteDecor[i]; whiteDecor[i]=whiteDecor[j]; whiteDecor[j]=t;
                }

        //裁定Black和White属于哪种规则的手牌
        blackGrade=new App().whichCardIsThis(blackNum,blackDecor);
        whiteGrade=new App().whichCardIsThis(whiteNum,whiteDecor);

        //判断输赢
        if (blackGrade>whiteGrade) return "Black wins";
        else if(blackGrade<whiteGrade) return "White wins";
        else {
            if (blackGrade==1||blackGrade==6){
                int j;
                for (j=5;j>0;j--)
                    if (blackNum[j]>whiteNum[j]){
                        return "Black wins";
                    }
                    else if (blackNum[j]<whiteNum[j]){
                        return "White wins";
                    }
                if (j==0) return "Tie";
            }
            else {
                if (blackNum[blackFlag]>whiteNum[whiteFlag]) return "Black wins";
                else if (blackNum[blackFlag]<whiteNum[whiteFlag]) return "White wins";
                else return "Tie";
            }
        }
        return "Tie";
    }

    public static void main(String[] args) {
        whiteNum[0]=1; blackNum[0]=2;
        whiteNum[6]=0; blackNum[6]=0;
        String str;
        Scanner sc = new Scanner(System.in);

        //读入BlackCards并转换格式、排序
        System.out.print("Black: ");
        for (int i=1;i<=5;i++) {
            str = sc.next();
            blackDecor[i]=new App().turnDecorToNum(str.charAt(1));
            blackNum[i]=new App().turnNumberToNum(str.charAt(0));
        }

        int t;
        for (int i=1;i<5;i++)
            for (int j=i+1;j<=5;j++)
                if (blackNum[i]>blackNum[j]){
                    t=blackNum[i]; blackNum[i]=blackNum[j]; blackNum[j]=t;
                    t=blackDecor[i]; blackDecor[i]=blackDecor[j]; blackDecor[j]=t;
                }

        //读入WhiteCards并转换格式、排序
        System.out.print("White: ");
        for (int i=1;i<=5;i++) {
            str = sc.next();
            whiteDecor[i]=new App().turnDecorToNum(str.charAt(1));
            whiteNum[i]=new App().turnNumberToNum(str.charAt(0));
        }

        for (int i=1;i<5;i++)
            for (int j=i+1;j<=5;j++)
                if (whiteNum[i]>whiteNum[j]){
                    t=whiteNum[i]; whiteNum[i]=whiteNum[j]; whiteNum[j]=t;
                    t=whiteDecor[i]; whiteDecor[i]=whiteDecor[j]; whiteDecor[j]=t;
                }

        //裁定Black和White属于哪种规则的手牌
        blackGrade=new App().whichCardIsThis(blackNum,blackDecor);
        whiteGrade=new App().whichCardIsThis(whiteNum,whiteDecor);
        
        //判断输赢
        if (blackGrade>whiteGrade) System.out.println("Black wins");
        else if(blackGrade<whiteGrade) System.out.println("White wins");
        else {
            if (blackGrade==1||blackGrade==6){
                int j;
                for (j=5;j>0;j--)
                    if (blackNum[j]>whiteNum[j]){
                        System.out.println("Black wins");
                        break;
                    }
                    else if (blackNum[j]<whiteNum[j]){
                        System.out.println("White wins");
                        break;
                    }
                if (j==1||j==0) System.out.println("Tie");
            }
            else {
                if (blackNum[blackFlag]>whiteNum[whiteFlag]) System.out.println("Black wins");
                else if (blackNum[blackFlag]<whiteNum[whiteFlag]) System.out.println("White wins");
                else System.out.println("Tie");
            }
        }

    }
}