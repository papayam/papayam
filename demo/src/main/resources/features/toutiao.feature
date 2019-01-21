Feature: 头条测试用例

  Scenario: 头条搜索关键字
    Given open_device"nexus6p"
    When click"id,com.ss.android.article.news:id/bam"
    When input"id,com.ss.android.article.news:id/uy,2019春节晚会"
    When click"id,com.ss.android.article.news:id/uv"
    When sleep"5"