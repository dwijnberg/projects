from graphics import *


def verse(number:int)->str:
    if number == 1:
        return "(and) a partridge in a pear tree"
    if number == 2:
        return "Two turtle doves\n" + verse(number-1)
    if number == 3:
        return "Three French hens\n" + verse(number-1)
    if number == 4:
        return "Four calling birds\n" + verse(number-1)
    if number == 5:
        return "Five gold rings\n" + verse(number-1)
    if number == 6:
        return "Six geese a-laying\n" + verse(number-1)
    if number == 7:
        return "Seven swans a-swimming\n" + verse(number-1)
    if number == 8:
        return "Eight maids a-milking\n" + verse(number-1)
    if number == 9:
        return "Nine ladies dancing\n"  + verse(number-1)
    if number == 10:
        return "Ten lords a-leaping\n" + verse(number-1)
    if number == 11:
        return "Eleven pipers piping\n" + verse(number-1)
    if number >= 12:
        return "Twelve drummers drumming\n" + verse(number-1)
    
def day(day: int)->str:
    if day == 1:
        return "On the " + str(day) + "st day of Christmas, my true love sent to me"
    if day == 2:
        return "On the " + str(day) + "nd day of Christmas, my true love sent to me"
    if day == 3:
        return "On the " + str(day) + "rd day of Christmas, my true love sent to me"
    if day > 3:
        return "On the " + str(day) + "th day of Christmas, my true love sent to me"

def snowman(win : GraphWin):
    c = Circle(Point(200-50,200-100), 50)
    c.setFill("white")
    c.setOutline("white")
    c.draw(win)
    c1 = Circle(Point(200-50,280-100), 75)
    c1.setOutline("white")
    c1.setFill("white")
    c1.draw(win)
    c2 = Circle(Point(200-50,375-100),100)
    c2.setOutline("white")
    c2.setFill("white")
    c2.draw(win)
    c3 = Circle(Point(180-50,180-100), 3)
    c3.setOutline("black")
    c3.setFill("black")
    c3.draw(win)
    c3 = Circle(Point(220-50,180-100), 3)
    c3.setOutline("black")
    c3.setFill("black")
    c3.draw(win)
    nose = Polygon(Point(200-50,185-100),Point(200-50,195-100),Point(240-50,190-100))
    nose.setOutline("orange")
    nose.setFill("orange")
    nose.draw(win)
    hat = Rectangle(Point(140-50,165-100),Point(260-50,155-100))
    hat.setOutline("black")
    hat.setFill("black")
    hat.draw(win)
    hat2 = Rectangle(Point(150-50,130-100), Point(250-50,165-100))
    hat2.setOutline("black")
    hat2.setFill("black")
    hat2.draw(win)

def main():
    win = GraphWin("12 Days Of Christmas", 1400, 800)
    win.setBackground(color_rgb(30, 121, 44))
    background = Image(Point(700,400), "background.png")
    background.draw(win)
    snowman(win)
    verseNum = 1
    bkgrd2 = Rectangle(Point(285, 100), Point(1115, 700))
    bkgrd2.setOutline("white")
    bkgrd2.setFill("white")
    bkgrd2.draw(win)
    picture = Image(Point(150,600), verse(verseNum).partition('\n')[0].replace(" ", "") + ".png")
    picture.draw(win)
    background = Image(Point(700,400), "background.png")
    song = Text(Point(700,400),day(verseNum) + "\n" + verse(verseNum))
    song.setSize(36)
    song.setFace("times roman")
    song.draw(win)
    while True:
        win.getMouse()
        verseNum += 1
        if (verseNum == 13):
            verseNum = 1
        song.undraw()
        picture.undraw()
        song = Text(Point(700,400),day(verseNum) + "\n" + verse(verseNum))
        song.setOutline("black")
        song.setSize(36)
        song.setFace("times roman")
        song.draw(win)
        picture = Image(Point(150,600), verse(verseNum).partition('\n')[0].replace(" ", "") + ".png")
        picture.draw(win)
        
    
main()