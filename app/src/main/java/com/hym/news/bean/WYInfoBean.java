package com.hym.news.bean;

import java.io.Serializable;
import java.util.List;

public class WYInfoBean implements Serializable {

    private List<T1348647853363Bean> T1348647853363;
    private static final long serialVersionUID = -1943961352036134112L;
    private int id;
    private String title;
    private String url;
    private boolean isShow;

    public WYInfoBean(int id, String title, String url, boolean isShow) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.isShow = isShow;
    }

    public WYInfoBean() {

    }

    public List<T1348647853363Bean> getT1348647853363() {
        return T1348647853363;
    }

    public void setT1348647853363(List<T1348647853363Bean> T1348647853363) {
        this.T1348647853363 = T1348647853363;
    }

    public static class T1348647853363Bean {
        /**
         * sourceId : T1348647853363
         * template : normal1
         * lmodify : 2020-05-03 11:11:42
         * source : 独角兽财经
         * postid : FBJU8H450539AT47
         * title : 董明珠:支持取消住房公积金 我们公司根本不需要
         * mtime : 2020-05-03 11:11:42
         * hasImg : 1
         * topic_background : http://cms-bucket.ws.126.net/2020/0422/1e44993bp00q966r20011c000u000a0c.png
         * digest : 近日，铁娘子董明珠再次语出惊人，直言赞成取消住房公积金，并且将在人大代表会中提出建议取消公积金的相关内容，此举引起了公众热议，但其中支持她的人却是寥寥无几。董小
         * boardid : dy_wemedia_bbs
         * alias : Top News
         * hasAD : 1
         * imgsrc : http://cms-bucket.ws.126.net/2020/0503/6a6303aej00q9qiy5000lc000s600e3c.jpg
         * ptime : 2020-05-03 10:54:42
         * daynum : 18385
         * hasHead : 1
         * order : 1
         * votecount : 23450
         * hasCover : false
         * docid : FBJU8H450539AT47
         * tname : 头条
         * url_3w :
         * priority : 100
         * url : https://3g.163.com/news/article/FBJU8H450539AT47.html
         * quality : 80
         * commentStatus : 1
         * ads : [{"subtitle":"","skipType":"photoset","skipID":"00AO0001|2308233","tag":"photoset","title":"新增确诊近万例 俄临时医院抓紧修建","imgsrc":"bigimg","url":"00AO0001|2308233"},{"subtitle":"","skipType":"photoset","skipID":"00AN0001|2308242","tag":"photoset","title":"一场飞越三国援助同胞的\"生死赛跑\"","imgsrc":"bigimg","url":"00AN0001|2308242"},{"subtitle":"","skipType":"photoset","skipID":"00AO0001|2308229","tag":"photoset","title":"华盛顿举行飞行表演致敬抗疫工作者","imgsrc":"bigimg","url":"00AO0001|2308229"},{"subtitle":"","skipType":"photoset","skipID":"00AO0001|2308228","tag":"photoset","title":"休斯敦经济\"重启\"首日 路人寥寥无几","imgsrc":"bigimg","url":"00AO0001|2308228"},{"subtitle":"","skipType":"photoset","skipID":"00AP0001|2308224","tag":"photoset","title":"云南出土万件明清时期器物","imgsrc":"http://cms-bucket.ws.126.net/2020/0502/0a854b61j00q9p2jy002gc000s600e3c.jpg","url":"00AP0001|2308224"}]
         * ename : iosnews
         * replyCount : 26307
         * ltitle : 董明珠:支持取消住房公积金 我们公司根本不需要
         * hasIcon : true
         * subtitle :
         * cid : C1348646712614
         */

        private String sourceId;
        private String template;
        private String lmodify;
        private String source;
        private String postid;
        private String title;
        private String mtime;
        private int hasImg;
        private String topic_background;
        private String digest;
        private String boardid;
        private String alias;
        private int hasAD;
        private String imgsrc;
        private String ptime;
        private String daynum;
        private int hasHead;
        private int order;
        private int votecount;
        private boolean hasCover;
        private String docid;
        private String tname;
        private String url_3w;
        private int priority;
        private String url;
        private int quality;
        private int commentStatus;
        private String ename;
        private int replyCount;
        private String ltitle;
        private boolean hasIcon;
        private String subtitle;
        private String cid;
        private List<AdsBean> ads;

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postid) {
            this.postid = postid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public int getHasImg() {
            return hasImg;
        }

        public void setHasImg(int hasImg) {
            this.hasImg = hasImg;
        }

        public String getTopic_background() {
            return topic_background;
        }

        public void setTopic_background(String topic_background) {
            this.topic_background = topic_background;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getDaynum() {
            return daynum;
        }

        public void setDaynum(String daynum) {
            this.daynum = daynum;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getUrl_3w() {
            return url_3w;
        }

        public void setUrl_3w(String url_3w) {
            this.url_3w = url_3w;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getQuality() {
            return quality;
        }

        public void setQuality(int quality) {
            this.quality = quality;
        }

        public int getCommentStatus() {
            return commentStatus;
        }

        public void setCommentStatus(int commentStatus) {
            this.commentStatus = commentStatus;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getLtitle() {
            return ltitle;
        }

        public void setLtitle(String ltitle) {
            this.ltitle = ltitle;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public static class AdsBean {
            /**
             * subtitle :
             * skipType : photoset
             * skipID : 00AO0001|2308233
             * tag : photoset
             * title : 新增确诊近万例 俄临时医院抓紧修建
             * imgsrc : bigimg
             * url : 00AO0001|2308233
             */

            private String subtitle;
            private String skipType;
            private String skipID;
            private String tag;
            private String title;
            private String imgsrc;
            private String url;

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getSkipType() {
                return skipType;
            }

            public void setSkipType(String skipType) {
                this.skipType = skipType;
            }

            public String getSkipID() {
                return skipID;
            }

            public void setSkipID(String skipID) {
                this.skipID = skipID;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
