<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/resources/css/style.css">
    </head>
    <body>
    <section>
        <br><br>
        <nav aria-label="Page navigation">
            <ul class="pagination" id="listing">
                <c:if test="${(pageNumber ne 1) && (pageNumber ne null)}">
                    <c:url var="changePagePrevious" value="${action}">
                        <c:param name="pageNumber" value="${pageNumber-1}"/>
                    </c:url>
                    <li class="page-item"><a class="page-link" href="${changePagePrevious}">Previous</a></li>
                </c:if>
                <c:forEach begin="1" end="${numberOfPages}" step="1" var="index">

                    <c:url var="changePageNext" value="${action}">
                        <c:choose>
                            <c:when test="${pageNumber == null}">
                                <c:param name="pageNumber" value="${2}"/>
                            </c:when>
                            <c:otherwise>
                                <c:param name="pageNumber" value="${pageNumber+1}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:url>

                    <c:url var="changePage" value="${action}">
                        <c:param name="pageNumber" value="${index}"/>
                    </c:url>

                    <li class="page-item"><a class="page-link" href="${changePage}">${index}</a></li>
                </c:forEach>
                <c:if test="${(pageNumber < numberOfPages) || ( (1 != numberOfPages) && (pageNumber == null))}">
                    <li class="page-item"><a class="page-link" href="${changePageNext}">Next</a></li>
                </c:if>
            </ul>
        </nav>
    </section>
    </body>
</html>