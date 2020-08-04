"""
This example shows connecting to the PN532 with I2C (requires clock
stretching support), SPI, or UART. SPI is best, it uses the most pins but
is the most reliable and universally supported.
After initialization, try waving various 13.56MHz RFID cards over it!
"""


from pn532 import *
import time
import requests
import RPi.GPIO as GPIO
pin =18
GPIO.setmode(GPIO.BCM)
GPIO.setup(pin, GPIO.OUT)
p= GPIO.PWM(pin, 50) 
p.start(0)
cnt = 0
         
def open():      
    print('open')
    p.ChangeDutyCycle(12.5) 
    time.sleep(2)
    p.ChangeDutyCycle(2.0)
    requests.get('http://192.168.0.88:8091/close')
    time.sleep(2)
   
  
if __name__ == '__main__':
    try:
        pn532 = PN532_SPI(debug=False, reset=20, cs=4)
        #pn532 = PN532_I2C(debug=False, reset=20, req=16)
        #pn532 = PN532_UART(debug=False, reset=20)

        ic, ver, rev, support = pn532.get_firmware_version()
        print('Found PN532 with firmware version: {0}.{1}'.format(ver, rev))

        # Configure PN532 to communicate with MiFare cards
        pn532.SAM_configuration()

        print('Waiting for RFID/NFC card...')
        while True:
            data2 = requests.get('http://192.168.0.88:8091/door')
            if data2.text == '1':
                open()
            # Check if a card is available to read
            uid = pn532.read_passive_target(timeout=0.5)
            print('.', end="")
            # Try again if no card is available.
            if uid is None:
                continue
            print('Found card with UID:', [hex(i) for i in uid])
            
            
            cardValue = '';
            for i in uid:
                cardValue = cardValue + str(i)
            print('cardValue=', cardValue)
            print('====')
            data = requests.get('http://192.168.0.88:8091/dooropen?&cardValue='+cardValue)
            requests.get('http://192.168.0.88:8091/open?&empNo=no')
            print(data.text)
            if data.text == '1':
                open()
            time.sleep(2)
    except Exception as e:
        print(e)
    finally:
        GPIO.cleanup()

