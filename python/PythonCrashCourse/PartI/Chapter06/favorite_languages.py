#!/usr/bin/python3

# A dictionary of similar objects
#favorite_languages = {
        #'jen': 'python',
        #'sarah': 'c',
        #'edward': 'ruby',
        #'phil': 'python',
        #}

# Looping through all key-value pairs
#for name, language in favorite_languages.items():
    #print(name.title() + "'s favorite language is " +
        #language.title() + ".")


# Looping through all the keys in a dictionary
#for name in favorite_languages.keys():
    #print(name.title())

#for name in favorite_languages:
    #print(name.title())

#friends = ['phil', 'sarah']
#for name in favorite_languages.keys():
    #print(name.title())

    #if name in friends:
        #print(" Hi " + name.title() +
                #", I see your favorite language is " +
                #favorite_languages[name].title() + "!")

#if 'erin' not in favorite_languages.keys():
    #print("Erin, please take our poll!")


# Looping through a dictionary's keys in order
#for name in sorted(favorite_languages.keys()):
    #print(name.title() + ", thank you for taking the poll.")


# Looping through all values in a dictionary
#print("The following languages have been mentioned:")
#for language in set(favorite_languages.values()):
    #print(language.title())

# A list in a dictionary
favorite_languages = {
        'jen': ['python', 'ruby'],
        'sarah': ['c'],
        'edward': ['ruby', 'go'],
        'phil': ['python', 'haskell'],
        }

for name, languages in favorite_languages.items():
    if len(languages) == 1:
        print("\n" + name.title() + "'s favorite language is " 
                + languages[0].title() + ".")
    else:
        print("\n" + name.title() + "'s favorite languages are: ")
        for language in languages:
            print("\t" + language.title())
