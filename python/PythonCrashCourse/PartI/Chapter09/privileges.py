#!/usr/bin/python3
"""Class user"""

class Privileges():
    """Model for privileges."""

    def __init__(self, privileges):
        """Initialize privilege attributes."""
        self.privileges = privileges

    def show_privileges(self):
        """Display user's privileges."""
        print("Privileges".center(17))
        for privilege in self.privileges:
            print(privilege.ljust(17))


#person = Person('humberto', 'perdomo')
#privileges = Privileges(["can add post", "can delete post", "can be user"])
#admin = Administrator(person, 'hperdomo', '!Q2w3e4r', 'humberto.perdomo@gmail.com', privileges)
#admin.greet_user()
#admin.show_privileges()
