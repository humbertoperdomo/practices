#!/usr/bin/python3


def displayInventory(inventory):
    print('Inventory:')
    total = 0
    for k, v in inventory.items():
        total += v
        print(str(v) + ' ' + k)
    print('Total number of items: ' + str(total))

def addToInventory(inventory, addedItems):
    print('Adding to inventory:')
    for item in addedItems:
        inventory.setdefault(item, 0)
        inventory[item] += 1
    return inventory

inv = {'gold coin':42, 'rope': 1}
dragonLoot = ['gold coin', 'dragger', 'gold coin', 'gold coin', 'ruby']
addToInventory(inv, dragonLoot)
displayInventory(inv)
