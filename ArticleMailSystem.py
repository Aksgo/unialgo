import smtplib as smt
import os
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import firebase_admin as fab
from firebase_admin import auth, credentials
import os
def mailSender(username, usermail, arturl, artTitle):
    smpt_server = 'smtp.gmail.com'
    smtp_port = 587
    provider = os.getenv("unialgo_provider")
    emps = os.getenv('EMPS')
    msg = MIMEMultipart()
    body = "Dear "+username+",\nUnialgo is back with another new concept !\n\nTopic :\n"+artTitle+"\n\n"
    btn = f"""
        <html>
        <head>
        <style>
        </style>
         <body>
            <a href='{arturl}' style=
            'display: flex;
            padding: 10px 20px; 
            margin:auto;
            border:20px solid 00000000; 
            font-size: 16px;
            color: #ffffff;
            width: fit-content;
            align-items:center;
            background: linear-gradient(45deg, #00c3ff, #951cff, #ff0080);
            text-decoration: none;
            border-radius: 10px;
            text-align: center;
            justify-content:center'>
            Learn Now
            </a>
         </body>
        </html>
    """
    msg['From']=provider
    msg['To']=usermail
    msg['Subject'] = "Hey! 🚀 New Concept for you to learn"
    msg.attach(MIMEText(body,'plain'))
    msg.attach(MIMEText(btn,'html'))
    s = smt.SMTP(smpt_server,smtp_port)
    try:
        s.starttls()
        s.login(provider,emps)
        s.sendmail(provider, usermail, msg.as_string())
    finally:    
        s.quit()

def extractUsers(arturl, artTitle):
    cred_path = os.getenv("service_account_firebase")
    cred = credentials.Certificate(cred_path)
    app = fab.initialize_app(cred)
    page = auth.list_users()
    for user in page.iterate_all():
        mailSender(user.display_name, user.email, arturl, artTitle)
        print("mail sent to :",user.email)

def main():
    artTitle = input("Enter article Title : ")
    arturl = input("Enter url of article: ")
    extractUsers(arturl, artTitle)

n = int(input("Enter no of articles : "))
while n>0:
    main()
    n-=1