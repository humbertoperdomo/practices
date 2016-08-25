#!/usr/bin/python3

TAB_SIZE = 4

def printPicnic(itemsDict, leftWidth, rightWidth):
    print('PICNIC ITEMS'.center(leftWidth + rightWidth, '-'))
    for k, v in itemsDict.items():
        print(k.ljust(leftWidth, '.') + str(v).rjust(rightWidth))

def getLongestKey(items):
    longest = ''
    for k in items.keys():
        if len(k) > len(longest):
            longest = k

    return len(longest)

picnicItems = {'sandwiches': 4, 'apples': 12, 'cups': 4, 'cookies': 8000}
printPicnic(picnicItems, getLongestKey(picnicItems) + TAB_SIZE, 6)
#printPicnic(picnicItems, 20, 6)
