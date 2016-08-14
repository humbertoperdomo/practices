#!/usr/bin/python3
#import user_functions
#from user_functions import build_profile
#from user_functions import build_profile as bp
#import user_functions as uf
from user_functions import *

user_profile = build_profile('albert', 'einstein', 
        location='princeton', 
        field='physics')
print(user_profile)

my_profile = build_profile('humberto', 'perdomo',
         location='guadalajara',
         field='computer science',
         favorite_food='hamburger')
print(my_profile)
