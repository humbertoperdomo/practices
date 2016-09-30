#!/usr/bin/python3
"""Class user"""
from user import User
from privileges import Privileges

class Administrator(User):
    """Model an administrator."""

    def __init__(self, person, user_name, user_password, user_email, privileges):
        """Initialize User attributes."""
        super().__init__(person, user_name, user_password, user_email)
        self.privileges = privileges

    def show_privileges(self):
        """Display user's privileges."""
        self.privileges.show_privileges()

#person = Person('humberto', 'perdomo')
#admin = Administrator(person, 'hperdomo', '!Q2w3e4r', 'humberto.perdomo@gmail.com')
#admin.greet_user()
#admin.show_privileges()
