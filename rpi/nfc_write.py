
import RPi.GPIO as GPIO
import requests
import pn532.pn532 as nfc
from pn532 import *
key_a = b'\xFF\xFF\xFF\xFF\xFF\xFF'

pn532 = PN532_SPI(debug=False, reset=20, cs=4)
#pn532 = PN532_I2C(debug=False, reset=20, req=16)
#pn532 = PN532_UART(debug=False, reset=20)

ic, ver, rev, support = pn532.get_firmware_version()
print('Found PN532 with firmware version: {0}.{1}'.format(ver, rev))

# Configure PN532 to communicate with MiFare cards
pn532.SAM_configuration()

print('Waiting for RFID/NFC card to write to!')
while True:
    # Check if a card is available to read
    uid = pn532.read_passive_target(timeout=0.5)
    
    print('.', end="")
    # Try again if no card is available.
    if uid is not None:
        break
        
data2 = requests.get('http://ec2-3-35-8-128.ap-northeast-2.compute.amazonaws.com:8080/insertnfc').text
print('Found card with UID:', [hex(i) for i in uid])
print(data2)
"""
Warning: DO NOT write the blocks of 4N+3 (3, 7, 11, ..., 63)
or else you will change the password for blocks 4N ~ 4N+2.

Note: 
1.  The first 6 bytes (KEY A) of the 4N+3 blocks are always shown as 0x00,
since 'KEY A' is unreadable. In contrast, the last 6 bytes (KEY B) of the 
4N+3 blocks are readable.
2.  Block 0 is unwritable. 
"""
# Write block #6
block_number = 2
if data2[0:1] == '1':
  num1 = int(data2[1:5],16)
  num2 = int(data2[5:9],16)
  num3 = int(data2[9:13],16)
  num4 = int(data2[13:17],16)
  key_a = b'\xFF\xFF\xFF\xFF\xFF\xFF'
  data = bytes([num1, num2, num3, num4, 0x00, 0x00, 0xFF, 0x07, 0x80, 0x69, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF])
  try:
        pn532.mifare_classic_authenticate_block(
            uid, block_number=block_number, key_number=nfc.MIFARE_CMD_AUTH_A, key=key_a)
        pn532.mifare_classic_write_block(block_number, data)
        if pn532.mifare_classic_read_block(block_number) == data:
            print('write block %d successfully' % block_number)
  except nfc.PN532Error as e:
        print(e.errmsg)
  GPIO.cleanup()
