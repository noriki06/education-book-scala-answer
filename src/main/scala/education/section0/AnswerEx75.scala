package education.section0

object AnswerEx75:

  case class Department(
    id: Department.Id,
    name: String
  )

  object Department:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  case class Employee(
    id: Employee.Id,
    name: String,
    departmentId: Department.Id,
    salary: Int,
    employmentType: Employee.EmploymentType
  )

  object Employee:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

    enum EmploymentType:
      case FullTime
      case Contract
      case PartTime

  def main(args: Array[String]): Unit =
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
        Employee(Employee.Id(1002), "Bob", Department.Id(1), 40, Employee.EmploymentType.Contract),
        Employee(Employee.Id(1003), "Carol", Department.Id(2), 60, Employee.EmploymentType.FullTime),
        Employee(Employee.Id(1004), "Dave", Department.Id(1), 45, Employee.EmploymentType.FullTime),
        Employee(Employee.Id(1005), "Eve", Department.Id(2), 55, Employee.EmploymentType.PartTime)
      )

    println(departments)
    println(employees)

    println(groupDepartment(employees))

    println(noneDepartment(employees, departments))


  def groupDepartment(employees: Seq[Employee]): Map[Department.Id, Seq[Employee]] =
    employees
      .groupBy(employee => employee.departmentId)

  def aveSalary(employees: Seq[Employee]): Map[Department.Id, Int] =
    groupDepartment(employees)
      .view
      .mapValues(employee => (employee.map(_.salary).sum) / (employee.size))
      .toMap

  def noneDepartment(employees: Seq[Employee], departments: Seq[Department]): Set[String] =
    val alldepartmentId = departments.map(depart => depart.id).toSet
    val bedepartment = employees.map(employee => employee.departmentId).toSet

    val nondep = alldepartmentId.diff(bedepartment).toSet

    departments
      .filter(department => nondep.contains(department.id))
      .map(_.name)
      .toSet
