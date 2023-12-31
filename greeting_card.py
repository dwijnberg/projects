from graphics import *
import time

def main():
    win = GraphWin("Greeting Card", 1200, 800)
    win.setBackground("firebrick3")
    t = Text(Point(600,50), "Happy Holidays")
    t.setSize(36)
    t.setTextColor("lightsteelblue1")
    t.setStyle("bold")
    t.draw(win)
    c = Circle(Point(200,200), 50)
    c.setFill("white")
    c.setOutline("white")
    c.draw(win)
    c1 = Circle(Point(200,280), 75)
    c1.setOutline("white")
    c1.setFill("white")
    c1.draw(win)
    c2 = Circle(Point(200,375),100)
    c2.setOutline("white")
    c2.setFill("white")
    c2.draw(win)
    c3 = Circle(Point(180,180), 3)
    c3.setOutline("black")
    c3.setFill("black")
    c3.draw(win)
    c3 = Circle(Point(220,180), 3)
    c3.setOutline("black")
    c3.setFill("black")
    c3.draw(win)
    nose = Polygon(Point(200,185),Point(200,195),Point(240,190))
    nose.setOutline("orange")
    nose.setFill("orange")
    nose.draw(win)
    hat = Rectangle(Point(140,165),Point(260,155))
    hat.setOutline("black")
    hat.setFill("black")
    hat.draw(win)
    hat2 = Rectangle(Point(150,130), Point(250,165))
    hat2.setOutline("black")
    hat2.setFill("black")
    hat2.draw(win)
    menorah = Image(Point(1000,300),"menorah.png")
    menorah.draw(win)
    outline = Rectangle(Point(1200,0), Point(0,800))
    outline1 = Rectangle(Point(1200-1,0+1), Point(0+1,800-1))
    outline2 = Rectangle(Point(1200-2,0+2), Point(0+2,800-2))
    outline3 = Rectangle(Point(1200-3,0+3), Point(0+3,800-3))
    outline4 = Rectangle(Point(1200-4,0+4), Point(0+4,800-4))
    outline5 = Rectangle(Point(1200-5,0+5), Point(0+5,800-5))
    outline6 = Rectangle(Point(1200-6,0+6), Point(0+6,800-6))
    outline7 = Rectangle(Point(1200-7,0+7), Point(0+7,800-7))
    outline8 = Rectangle(Point(1200-8,0+8), Point(0+8,800-8))
    outline9 = Rectangle(Point(1200-9,0+9), Point(0+9,800-9))
    outline10 = Rectangle(Point(1200-10,0+10), Point(0+10,800-10))
    outline.setOutline("white")
    outline1.setOutline("white")
    outline2.setOutline("white")
    outline3.setOutline("white")
    outline4.setOutline("white")
    outline5.setOutline("white")
    outline6.setOutline("white")
    outline7.setOutline("white")
    outline8.setOutline("white")
    outline9.setOutline("white")
    outline10.setOutline("white")
    outline.draw(win)
    outline1.draw(win)
    outline2.draw(win)
    outline3.draw(win)
    outline4.draw(win)
    outline5.draw(win)
    outline6.draw(win)
    outline7.draw(win)
    outline8.draw(win)
    outline9.draw(win)
    outline10.draw(win)
    
    names = ["tree1.gif", "tree2.gif", "tree3.gif", "tree4.gif"]
    x=0
    trees = []
    elfnames = ["elf.gif", "elf2.gif", "elf3.gif", "elf4.gif", "elf5.gif", "elf6.gif", "elf7.gif", "elf8.gif"]
    elfnum=0
    while True:
        tree = Image(Point(600, 300), names[x])
        elf = Image(Point(50, 50), elfnames[elfnum])
        elf.draw(win)
        tree.draw(win)
        if trees:
            trees[0].undraw()
        trees.insert(0,tree)
        elfnum += 1
        x +=1
        if x == 4:
            x = 0
        if elfnum == 8:
            elfnum = 0
        time.sleep(0.1)
        elf.undraw()
        
main()
