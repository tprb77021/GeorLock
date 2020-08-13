
from pn532 import *
import time
import requests
import RPi.GPIO as GPIO
import pn532.pn532 as nfc
key_a = b'\xFF\xFF\xFF\xFF\xFF\xFF'
pin =18
GPIO.setmode(GPIO.BCM)
GPIO.setup(pin, GPIO.OUT)
p= GPIO.PWM(pin, 50) 
p.start(0)
cnt = 0
SERVER_IP='http://ec2-3-35-8-128.ap-northeast-2.compute.amazonaws.com:8080'
pn532 = PN532_SPI(debug=False, reset=20, cs=4)

def open():      
    print('open')
    requests.get(SERVER_IP+'/open?empNo=no')
    p.ChangeDutyCycle(12.5) 
    time.sleep(2)
    p.ChangeDutyCycle(2.0)
    requests.get(SERVER_IP+'/close')
    time.sleep(2)


ic, ver, rev, support = pn532.get_firmware_version()
while True:    
  
  try:
      pn532.SAM_configuration()
      print('Found PN532 with firmware version: {0}.{1}'.format(ver, rev))
      
      # Configure PN532 to communicate with MiFare cards
      
      
      print('Waiting for RFID/NFC card...')
      while True:
          data2 = requests.get(SERVER_IP+'/door')
          if data2.text == '1':
            open()
          # Check if a card is available to read
          uid = pn532.read_passive_target(timeout=0.5)
          
          print('.', end="")
          # Try again if no card is available.
          if uid is not None:
              break
              
      data2 = requests.get(SERVER_IP+'/insertNfc').text
      print('Found card with UID:', [hex(i) for i in uid])
      print(data2)
      
      # Write block #6
      block_number = 2
      if data2[0:1] == '1':
        num1 = int(data2[1:5],16)
        num2 = int(data2[5:9],16)
        num3 = int(data2[9:13],16)
        num4 = int(data2[13:17],16)
        data = bytes([num1, num2, num3, num4, 0x00, 0x00, 0xFF, 0x07, 0x80, 0x69, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF])
        pn532.mifare_classic_authenticate_block(uid, block_number=block_number, key_number=nfc.MIFARE_CMD_AUTH_A, key=key_a)
        pn532.mifare_classic_write_block(block_number, data)
        if pn532.mifare_classic_read_block(block_number) == data:
          print('write block %d successfully' % block_number)
          requests.get(SERVER_IP+'/resetNfc')
        
      else : 
        
                  # Check if a card is available to read
         
                  
          print('.', end="")
                  # Try again if no card is available.
          cardValue = ''
          NFCdata=''
          for i in uid:
                  cardValue = cardValue + str(i)
          print('cardValue=', cardValue)
          pn532.mifare_classic_authenticate_block(
          uid, block_number=2, key_number=nfc.MIFARE_CMD_AUTH_A, key=key_a)
          NFCdata = ''.join(['%02X' % x for x in pn532.mifare_classic_read_block(2)])   
          print('test =', NFCdata)
          print('====')
          data = requests.get(SERVER_IP+'/doorOpen?NFCdata='+NFCdata)
          print(data.text)
                  
          if data.text == '1':
                  open()
          time.sleep(4)
                    
  except Exception as e:
          print(e)
