import os
from bs4 import BeautifulSoup
import shutil

n = int(input("Enter number of files: "))

def main():
    inp = input("Enter as <art_name,img_name> :")
    artname, imgname = map(str.strip, inp.split(","))
    base_name = os.path.splitext(artname)[0]
    author = ""
    data = []

    # Supported image extensions
    image_extensions = [".png", ".jpg", ".jpeg"]

    # Function to check if image exists
    def check_image_exists(imgname):
        for ext in image_extensions:
            img_path = f"asset/ArticleImage/{imgname}{ext}"
            if os.path.exists(img_path):
                return img_path
        raise FileNotFoundError(f"Image with name {imgname} not found in supported formats ({', '.join(image_extensions)})")


    def extract_article():
        nonlocal author
        with open(f"{base_name}.txt", "r", encoding="utf-8") as file:
            complete = [line.strip() for line in file.readlines()]
        author = complete.pop().strip()

        para = ""
        for line in complete:
            if line == "":
                data.append(para)
                para = ""
            else:
                para += line + " "
        if para:
            data.append(para)

        gitignore_path = os.path.join(os.getcwd(), ".gitignore")
        with open(gitignore_path, "r", encoding="utf-8") as f:
            ignored = f.read().splitlines()
        if f"{base_name}.txt" not in ignored:
            with open(gitignore_path, "a", encoding="utf-8") as f:
                f.write(f"\n{base_name}.txt")


    def set_article():
        with open("public/ArticleTemplate.html", "r", encoding="utf-8") as file:
            content = file.read()
        soup = BeautifulSoup(content, 'html.parser')


        title = soup.find(class_='title')
        title.string = data[0]
        title.append(soup.new_tag('hr', id="line"))


        para_div = soup.find(class_='content')
        for i in range(2, len(data)):
            new_para = soup.new_tag('p')
            new_para.string = data[i]
            para_div.append(new_para)


        author_tag = soup.find(class_='author')
        author_tag.string = f"Written by: {author}"

        with open(f"public/ArticleList/{base_name}.html", "w", encoding="utf-8") as file:
            file.write(str(soup))


    def set_meta_description():
        with open("public/article.html", "r", encoding="utf-8") as file:
            content = file.read()
        soup = BeautifulSoup(content, 'html.parser')
        arlist = soup.find(class_='articles')

        # Check for existing article with the same base name
        check_article = soup.find(id=base_name)

        # Check for existing image with different extensions
        article_img_path = check_image_exists(imgname)


        relink = soup.new_tag('a', href=f"ArticleList/{base_name}.html")
        head_article = soup.new_tag('div', **{'class': 'head-article'})

        # Add image
        article_img = soup.new_tag('img', src=article_img_path, **{'class': 'article-img'})
        head_article.append(article_img)


        h2_tag = soup.new_tag('h2', **{'class': 'article-title'})
        h2_tag.string = data[0]
        head_article.append(h2_tag)
        
        relink.append(head_article)
        relink.append(soup.new_tag('hr'))


        art_desc = data[1]
        desc_tag = soup.new_tag('p', **{'class': 'article-description'})
        desc_tag.string = art_desc
        relink.append(desc_tag)


        if check_article:
            check_article.clear()
            check_article.append(relink)
        else:
            article = soup.new_tag('div', id=base_name, **{'class': 'article'})
            article.append(relink)
            arlist.append(article)

        
        with open("public/article.html", "w", encoding="utf-8") as file:
            file.write(str(soup))

    extract_article()
    set_meta_description()
    set_article()

#added the error handling
for i in range(n):
    try:
        main()
    except Exception as error:
        print(f"Error processing file: {error}")
