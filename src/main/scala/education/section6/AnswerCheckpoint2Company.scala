package education.section6

object AnswerCheckpoint2Company:

  case class Department(
    id:   Department.Id,
    name: String
  )

  object Department:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  case class Employee(
    id:             Employee.Id,
    name:           String,
    departmentId:   Department.Id,
    salary:         Int,
    employmentType: Employee.EmploymentType
  )

  object Employee:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

    enum EmploymentType:
      case FullTime,
      Contract,
      PartTime

  val departments: Seq[Department] =
    Seq(
      Department(Department.Id(1), "営業部"),
      Department(Department.Id(2), "開発部"),
      Department(Department.Id(3), "人事部"),
      Department(Department.Id(4), "広報部")
    )

  val employees: Seq[Employee] =
    Seq(
      Employee(Employee.Id(1001), "Alice", Department.Id(2), 50, Employee.EmploymentType.FullTime),
      Employee(Employee.Id(1002), "Bob",   Department.Id(1), 40, Employee.EmploymentType.Contract),
      Employee(Employee.Id(1003), "Carol", Department.Id(2), 60, Employee.EmploymentType.FullTime),
      Employee(Employee.Id(1004), "Dave",  Department.Id(1), 45, Employee.EmploymentType.FullTime),
      Employee(Employee.Id(1005), "Eve",   Department.Id(2), 55, Employee.EmploymentType.PartTime)
    )

  def main(args: Array[String]): Unit =
    //問１
    println(departments)
    println(employees)
    //問２
    println(groupByDepartment(employees))
    //問３
    println(averageSalaryByDepartment(employees))
    //問４
    println(findNoPersonDepartment(departments, employees))

  /**
   *部署ごとに社員を振り分ける
   */
  def groupByDepartment(employees: Seq[Employee]): Map[Department.Id, Seq[Employee]] =
    employees
      .groupBy(employee => employee.departmentId)

  /**
   *部署ごとの平均給与を出す（正社員のみ）
   */
  def averageSalaryByDepartment(employees: Seq[Employee]): Map[Department.Id, Int] =
    val employeeFulltime =
      employees
        .filter(employee => employee.employmentType == Employee.EmploymentType.FullTime)

    groupByDepartment(employeeFulltime)
      .view
      .mapValues(employeesInDepartment => (employeesInDepartment.map(_.salary).sum) / (employeesInDepartment.size))
      .toMap

  /**
   *社員のいない部署を探す
   */
  def findNoPersonDepartment(departments: Seq[Department], employees: Seq[Employee]): Set[String] =
    val allDepartmentId =
      departments
        .map(department => department.id)
        .toSet

    val affiliationDepartmentId =
     employees
       .map(employee => employee.departmentId)
       .toSet

    val noPersonDepartmentId =
      allDepartmentId
        .diff(affiliationDepartmentId)

    departments
      .filter(department => noPersonDepartmentId.contains(department.id))
      .map(department => department.name)
      .toSet
