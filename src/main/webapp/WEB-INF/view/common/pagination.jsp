<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <br><br>
    <nav aria-label="Page navigation">
        <ul class="pagination d-flex justify-content-center py-3" id="listing">
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