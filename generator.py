from bs4 import BeautifulSoup as bs
n = int(input("enter no of files : "))
def main():
    inp = input("Enter as <art_name,img_name> :")
    artname, imgname = tuple(inp.split(","))
    imgname = imgname.strip(" ")
    artname = artname.split(".")[0]
    artname = artname.strip(" ")
    data = []
    def extractArticle():
        with open(artname+".txt", "r") as f:
            complete  =  f.readlines()
            for i in range(0,len(complete)):
                complete[i] = complete[i].replace('\n',' ')
            if(complete[-1]!=' '):
                complete.append(' ')
            para=""
            for line in complete:
                if(line==' '):
                    data.append(para)
                    para=""
                else:
                    para = para + line
        gitfile = open('.gitignore','r+')
        ignored = gitfile.readlines()
        print(ignored)
        toignore = artname+".txt"
        if toignore not in ignored:
            ignored.append("\n"+toignore)
            gitfile.seek(0)
            gitfile.writelines(ignored)
        gitfile.close()
    def SetArticle():
            '''to create the page fo reach article'''
            f = open('public/ArticleTemplate.html','r',encoding='utf-8')
            content = f.read()
            soup = bs(content, 'html.parser')
            #filling the title 
            title = soup.find(class_='title')
            title.string = data[0]
            hr_tag = soup.new_tag('hr', id="line")
            title.append(hr_tag)
            #filling the article data
            #txt file structure
            #title\ndescription\nArticlePara
            para =  soup.find(class_='content')
            for i in range(2,len(data)):
                line = data[i]
                p_tag = soup.new_tag('p')
                p_tag.string = line
                para.append(p_tag)
            f.close()
            wf = open('public/ArticleList/'+artname+'.html', 'w')
            wf.write(soup.prettify())
            wf.close()
    def setMetaDescription():
        '''function to fill small title and description on list page'''
        f=open('public/article.html','r', encoding='utf-8')
        content = f.read()
        soup = bs(content, 'html.parser')
        arlist = soup.find(class_='articles')
        checkarticle =soup.find(id=artname)
        relink = soup.new_tag('a',href='ArticleList/'+artname+'.html')
        head_article = soup.new_tag('div', attrs={'class' : 'head-article'})
        article_img =soup.new_tag('img', src='asset/ArticleImage/'+imgname, attrs={'class':'article-img'})
        head_article.append(article_img)
        h2_tag = soup.new_tag('h2', attrs={'class':'article-title'})
        h2_tag.string = data[0]
        head_article.append(h2_tag)
        relink.append(head_article)
        line_tag = soup.new_tag('hr')
        relink.append(line_tag)
        art_desc = data[1]
        desc_tag = soup.new_tag('p', attrs={'class':'article-description'})
        desc_tag.string = art_desc
        relink.append(desc_tag)
        if checkarticle is not None:
            checkarticle.clear()
            checkarticle.append(relink)
            arlist.append(checkarticle)
        else:
            article = soup.new_tag('div', attrs={'class':'article', 'id':artname})
            article.append(relink)
            arlist.append(article)
        f.close()
        wf = open('public/article.html','w',encoding='utf-8')
        wf.write(soup.prettify())
        wf.close()
    extractArticle()
    setMetaDescription()
    SetArticle()
while(n>0):
    main()
    n-=1