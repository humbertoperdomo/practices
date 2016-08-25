#!/usr/bin/python3
# bullet_point_adder.py
"""Adds Wikipedia bullet points to the start
of each line of text on the clipboard.
"""
import pyperclip

TEXT = pyperclip.paste()

# Separate lines and add stars.
LINES = TEXT.split('\n')
for i, line in enumerate(LINES):      # loop through all indexes in the "lines" list
    LINES[i] = '* ' + LINES[i]   # add star to each string in "lines" list

TEXT = '\n'.join(LINES)

pyperclip.copy(TEXT)
