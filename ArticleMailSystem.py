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
    btn = f"""
    <html>
    <head>
        <style>
            body {{
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
                text-align: center;
            }}
            .container {{
                width: 70%;
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }}
            .button {{
                display: inline-flex;
                padding: 15px 30px;
                margin-top: 20px;
                border: none;
                font-size: 16px;
                color: #ffffff;
                background: linear-gradient(45deg, #00c3ff, #951cff, #ff0080);
                text-decoration: none;
                border-radius: 10px;
                align-items: center;
                justify-content: center;
                text-align: center;
                transition: background 0.3s ease;
            }}
            .button:hover {{
                background: linear-gradient(45deg, #00aaff, #8000cc, #e60073);
            }}
            h1 {{
                color: #333333;
                font-size: 24px;
                margin-bottom: 10px;
            }}
            p {{
                color: #555555;
                font-size: 16px;
                line-height: 1.6;
            }}
            a{{
                color: white;
            }}
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Hey {username},Unialgo's New Concept is here!</h1>
            <p>{artTitle}</p>
            <a href="{arturl}" class="button">Learn Now</a>
        </div>
    </body>
    </html>
    """

    msg['From'] = provider
    msg['To'] = usermail
    msg['Subject'] = "🚀 New Coding Concept for You to Learn"
    msg.attach(MIMEText(btn, 'html'))

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
        break

def main():
    artTitle = input("Enter article Title : ")
    arturl = input("Enter url of article: ")
    extractUsers(arturl, artTitle)

n = int(input("Enter no of articles : "))
while n>0:
    main()
    n-=1