#!/usr/bin/python3
"""Word count."""

def count_words(file_name):
    """Count the approximate number of words in a file."""
    try:
        with open(file_name) as f_obj:
            contents = f_obj.read()
    except FileNotFoundError:
        #msg = "Sorry, the file " + file_name + " does not exist."
        #print(msg)
        pass
    else:
        # Count the approximate number of words in the file.
        words = contents.split()
        num_words = len(words)
        print("The file " + file_name + " has about " + str(num_words) + " words.")

file_names = ["text_files/alice.txt", "text_files/siddhartha.txt", "text_files/moby_dick.txt", "text_files/little_women.txt"]
for file_name in file_names:
    count_words(file_name)
