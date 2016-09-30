#!/usr/bin/python3
"""Common words."""

word = "the"
files = ["text_files/alice.txt", "text_files/little_women.txt",
         "text_files/moby_dick.txt"]

for file_ in files:
    count = 0
    try:
        with open(file_) as f_obj:
            lines = f_obj.readlines()
            for line in lines:
                count += line.lower().count(word)
            print("The word '" + word + "' appears " + str(count)
                  + " times in the file " + file_)
    except FileNotFoundError:
        pass
