#!/usr/bin/python3

def collatz(number):
    if number & 1 == 0:
        result = number // 2
    else:
        result = 3 * number + 1
    
    return result

def gotNumber():
    gotNumber = False
    print('Enter a number: ')
    while not gotNumber:
        try:
            number = int(input())
            gotNumber = True
        except ValueError:
            print('You must enter an integer.')
    return number

def main():
    clltz = gotNumber()
    while clltz != 1:
        try:
            clltz = collatz(clltz)
            print(clltz)
        except ValueError:
            print('You must enter an integer.')

main()
