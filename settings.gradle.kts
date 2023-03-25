rootProject.name = "Xozyain"
include("Banks")
include("CatsService")
include("CatsService:DAL")
findProject(":CatsService:DAL")?.name = "DAL"
include("CatsService:Service")
findProject(":CatsService:Service")?.name = "Service"
include("CatsService:Controller")
findProject(":CatsService:Controller")?.name = "Controller"
