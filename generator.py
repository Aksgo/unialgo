from bs4 import BeautifulSoup as bs
import os
import platform
import subprocess

n = int(input("Enter number of operations (new/modify articles): "))

def main():
    choice = input("Would you like to add a new article or update an existing one? (new/update): ").strip().lower()
    
    if choice == "new":
        addNewArticle()
    elif choice == "update":
        modifyArticle()
    else:
        print("Invalid choice. Please enter 'new' or 'update'.")

def addNewArticle():
    inp = input("Enter as <art_name,img_name> :")
    artname, imgname = tuple(inp.split(","))
    imgname = imgname.strip(" ")
    artname = artname.split(".")[0]
    artname = artname.strip(" ")
    author = ""
    data = []
    
    # Extract and handle the new article
    author = extractArticle(artname, data)
    setMetaDescription(artname, data, imgname)
    SetArticle(artname, data, author)

def extractArticle(artname, data):
    ''' Extract the article data from the .txt file '''
    with open(artname+".txt", "r") as f:
        complete = f.readlines()
        complete = [line.replace('\n',' ') for line in complete]
        author = complete[-1].strip()  # Last line is the author's name
        complete = complete[:-1]  # Remove author line from article data
        if complete[-1] != ' ':
            complete.append(' ')  # Ensure a space separates paragraphs
        para = ""
        for line in complete:
            if line == ' ':
                data.append(para)
                para = ""
            else:
                para += line

    # Add txt file to .gitignore
    with open('.gitignore', 'r+') as gitfile:
        ignored = gitfile.readlines()
        toignore = artname+".txt"
        if toignore not in ignored:
            gitfile.write("\n" + toignore)
    
    return author

def SetArticle(artname, data, author):
    ''' Create HTML page for the article '''
    with open('public/ArticleTemplate.html', 'r', encoding='utf-8') as f:
        content = f.read()
        soup = bs(content, 'html.parser')

    # Set title and article data in the HTML template
    title = soup.find(class_='title')
    title.string = data[0]
    title.append(soup.new_tag('hr', id="line"))

    para = soup.find(class_='content')
    for i in range(2, len(data)):
        p_tag = soup.new_tag('p')
        p_tag.string = data[i]
        para.append(p_tag)

    # Set author name
    author_div = soup.find(class_='author')
    author_div.string = f"Written by: {author}"

    # Save the updated HTML file
    with open(f'public/ArticleList/{artname}.html', 'w', encoding='utf-8') as wf:
        wf.write(soup.prettify())

def setMetaDescription(artname, data, imgname):
    ''' Add or update the article on the main article list page '''
    with open('public/article.html', 'r', encoding='utf-8') as f:
        content = f.read()
        soup = bs(content, 'html.parser')

    arlist = soup.find(class_='articles')
    checkarticle = soup.find(id=artname)

    relink = soup.new_tag('a', href=f'ArticleList/{artname}.html')
    head_article = soup.new_tag('div', attrs={'class': 'head-article'})
    
    # Add image and title
    article_img = soup.new_tag('img', src=f'asset/ArticleImage/{imgname}', attrs={'class':'article-img'})
    head_article.append(article_img)
    h2_tag = soup.new_tag('h2', attrs={'class':'article-title'})
    h2_tag.string = data[0]
    head_article.append(h2_tag)
    relink.append(head_article)
    
    relink.append(soup.new_tag('hr'))
    
    # Add description
    desc_tag = soup.new_tag('p', attrs={'class':'article-description'})
    desc_tag.string = data[1]
    relink.append(desc_tag)

    if checkarticle:
        checkarticle.clear()
        checkarticle.append(relink)
    else:
        article = soup.new_tag('div', attrs={'class': 'article', 'id': artname})
        article.append(relink)
        arlist.append(article)

    with open('public/article.html', 'w', encoding='utf-8') as wf:
        wf.write(soup.prettify())

def modifyArticle():
    ''' Modify an existing article by opening its .txt file in a text editor '''
    artname = input("Enter the name of the article to update (without extension): ").strip()
    imgext = input("enter image type (png/jpg):")
    txt_file = f'{artname}.txt'
    
    # Check if the article's .txt file exists
    if not os.path.exists(txt_file):
        print(f"Error: No .txt file found with the name '{artname}'.")
        return

    # Open the .txt file in the default text editor (e.g., Notepad for Windows)
    print(f"Opening {txt_file} for editing...")
    
    if platform.system() == 'Windows':
        subprocess.run(["notepad.exe", txt_file])  # Waits until Notepad is closed
    elif platform.system() == 'Darwin':  # macOS
        subprocess.run(["open", "-t", txt_file])  # Wait for TextEdit on macOS
    else:
        subprocess.run(["xdg-open", txt_file])  # For Linux systems, waits for the default editor to close

    # After the editor is closed, regenerate the HTML article
    data = []
    
    author = extractArticle(artname, data)  # Extract updated content
    setMetaDescription(artname, data, f'{artname}.{imgext}')  # Assuming image name is same as article name and in .jpg format (change if needed)
    SetArticle(artname, data, author)  # Update the HTML based on the new content

    print(f"Article '{artname}' updated successfully!")

while n > 0:
    main()
    n -= 1
