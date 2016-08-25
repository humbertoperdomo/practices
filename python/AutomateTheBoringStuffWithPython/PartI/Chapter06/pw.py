#!/usr/bin/python3
# pw.py - An insecure password locker program.
"""Project: Password locker
"""
import sys
import pyperclip

PASSWORDS = {'email': 'E@BkV&l6FZ+P]J5z)tOj@dy0>ziT$j4D',
             'blog': 'axAgb3P-w)ek}$A#0o6*pb^x|[db=7Jh',
             'luggage': '12345'}

def print_usage():
    """Prints a usage message of the program
    """
    print('Usage: python pw.py [account] - copy account password')
    sys.exit()

if len(sys.argv) < 2:
    print_usage()

ACCOUNT = sys.argv[1]   # first command line arg is the account name

if ACCOUNT in PASSWORDS:
    pyperclip.copy(PASSWORDS[ACCOUNT])
    print('Password for ' + ACCOUNT + ' copied to clipboard.')
else:
    print('There is no account named ' + ACCOUNT)
