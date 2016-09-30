#!/usr/bin/python3
"""Importing multiple classes."""
from admin import Administrator
from user import Person
from privileges import Privileges

person = Person('humberto', 'perdomo')
privileges = Privileges(["can add post", "can delete post", "can be user"])
admin = Administrator(person, 'hperdomo', '!Q2w3e4r',
                      'humberto.perdomo@gmail.com', privileges)
admin.greet_user()
admin.show_privileges()
