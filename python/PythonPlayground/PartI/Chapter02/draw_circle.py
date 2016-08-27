#!/usr/bin/python3
# draw_circle.py
"""Draw a circle
"""
import math
import turtle

def draw_circle_turtle(x, y, r):
    """Draw the circle using turtle
    """
    # move to the start of circle
    turtle.up()
    turtle.setpos(x + r, y)
    turtle.down()

    # draw the circle
    for i in range(0, 361, 1):
        a = math.radians(i)
        turtle.setpos(x + r * math.cos(a), y + r * math.sin(a))

draw_circle_turtle(100, 100, 50)
turtle.mainloop()
